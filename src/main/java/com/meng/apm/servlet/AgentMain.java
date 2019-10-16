package com.meng.apm.servlet;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;

public class AgentMain implements ClassFileTransformer {
	// 在应用启动前调用
	public static void premain(String agentArgs, Instrumentation inst) {
		inst.addTransformer(new AgentMain());
	}

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// 屏蔽
		String target = "javax.servlet.http.HttpServlet";
		if (target.equals(className)) {
			try {
				return buildClass(target, loader);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public byte[] buildClass(String target, ClassLoader loader)
			throws NotFoundException, IOException, CannotCompileException {
		ClassPool pool = new ClassPool();
		pool.insertClassPath(new LoaderClassPath(loader));
		CtClass cla = pool.get(target);
		CtClass[] params = new CtClass[] {
				pool.get("javax.servlet.http.HttpServletRequest"),
				pool.get("javax.servlet.http.HttpServletResponse")
		};
		CtMethod method = cla.getDeclaredMethod("service", params);
		// ClassNotFound 
		method.insertBefore("com.meng.apm.servlet.DispatcherServletCollecct.begin($args");
		pool.get("com.meng.apm.servlet.DispatcherServletCollecct").toClass(loader,null);
		return cla.toBytecode();

	}
}
