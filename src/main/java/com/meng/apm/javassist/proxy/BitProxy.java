package com.meng.apm.javassist.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;

public class BitProxy {
	public static Method getMethod(Class<?> cla, String name, Class... types) {
		try {
			return cla.getMethod(name, types);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * bit 动态代理对象构建
	 * 
	 * @param loader
	 * @param proxyTarget
	 * @param invocationHandler
	 */
	public void newProxyInstance(ClassLoader loader, Class<?> proxyTarget,
			InvocationHandler invocationHandler) throws NotFoundException,
			CannotCompileException, IllegalAccessException,
			InstantiationException, NoSuchMethodException,
			InvocationTargetException {
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(proxyTarget.getClassLoader()));
		CtClass targetClass = pool.get(proxyTarget.getName());
		CtClass proxyClass = pool.makeClass(proxyTarget.getName() + "$proxy",
				targetClass);
		CtField handlerField = new CtField(pool.get(InvocationHandler.class
				.getName()), "h", proxyClass);
		proxyClass.addField(handlerField);

		int methodIndex = 0;
		for (CtMethod ctMethod : targetClass.getDeclaredMethods()) {
			if (!AccessFlag.isPublic(ctMethod.getModifiers())) {
				continue;
			} else if ((ctMethod.getModifiers() & AccessFlag.NATIVE) != 0) {
				continue;
			} else if ((ctMethod.getModifiers() & AccessFlag.STATIC) != 0) {
				continue;
			} else if ((ctMethod.getModifiers() & AccessFlag.FINAL) != 0) {
				continue;
			}
			String methodFname = ctMethod.getName() + methodIndex;
			CtField methodField = new CtField(pool.get(Method.class.getName()),
					methodFname, proxyClass);
			String paramTypeSrc = "new Class[]{";
			for (int i = 0; i < ctMethod.getParameterTypes().length; i++) {
				if (i != 0)
					paramTypeSrc += ",";
				paramTypeSrc += ctMethod.getParameterTypes()[i].getName()
						+ ".class";
			}
			paramTypeSrc += "}";
			String d = proxyTarget.getName() + ".class";
			String initSrc = getClass().getName()+".getMethod(" + d + ",\""
					+ ctMethod.getName() + "\"," + paramTypeSrc + ")";

			proxyClass.addField(methodField, initSrc);

			CtMethod copymethod = CtNewMethod.copy(ctMethod, proxyClass, null);

			String bodySrc = "{";
			bodySrc += "Object result=h.invoke($0," + methodFname + ",$args);";

			if (!copymethod.getReturnType().getName().equals("void")) {
				bodySrc += "return ($r)result;";
			}
			bodySrc += "}";

			copymethod.setBody(bodySrc);
			proxyClass.addMethod(copymethod);
			methodIndex++;
		}
		CtConstructor constructor = new CtConstructor(
				new CtClass[] { pool.get(InvocationHandler.class.getName()) },
				proxyClass);
		constructor.setBody("h=$1;");
		proxyClass.addConstructor(constructor);

		proxyClass
				.debugWriteFile("E:\\git\\bit-teaching\\bit-javassist\\target\\");
		Class cla = proxyClass.toClass();
		UserServiceImpl i = (UserServiceImpl) cla.getConstructor(
				InvocationHandler.class).newInstance(invocationHandler);
		i.getName("11");
	}

	public static void main(String[] args) throws Exception {
		new BitProxy().newProxyInstance(BitProxy.class.getClassLoader(),
				UserServiceImpl.class, new InvocationHandler() {
					UserServiceImpl target = new UserServiceImpl();

					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						System.out.println("代理前");
						Object r = method.invoke(target, args);
						System.out.println("代理后");
						return r;
					}
				});
	}
}
