package com.meng.apm.javassist.simple;

import javassist.*;

public class TestJavassist {


    public static void main(String[] args) {
        ClassPool pool = new ClassPool(true);

        //插入类路径，更具类路径查找我们需要的类
        pool.insertClassPath(new LoaderClassPath(TestJavassist.class.getClassLoader()));
        CtClass targetClass = pool.makeClass("com.meng.hello");

        try {
            //实现一个接口
            targetClass.addInterface(pool.get(IHello.class.getName()));
            CtClass returnType = pool.get(void.class.getName());
            String mname = "sayHello";
            CtClass[] params = new CtClass[]{pool.get(String.class.getName())};
            CtMethod ctMethod = new CtMethod(returnType,mname, params, targetClass);

            String src = "{ " +
                    "System.out.println($0);" +
                    "System.out.println($args);" +
                    "System.out.println($sig);" +
                    "System.out.println($type);" +
                    "System.out.println($class);" +
                    "System.out.println(\"sayHello : \" + $1);" +
                    " }";
            ctMethod.setBody(src);
            targetClass.addMethod(ctMethod);
            Class cla = targetClass.toClass();
            IHello hello = (IHello) cla.newInstance();
            hello.sayHello("meng");
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public interface IHello {
        void sayHello(String name);
    }

}
