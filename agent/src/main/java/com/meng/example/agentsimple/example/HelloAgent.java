package com.meng.example.agentsimple.example;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class HelloAgent {

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     *
     * 在这个 premain 函数中，开发者可以进行对类的各种操作。
     * 1、agentArgs 是 premain 函数得到的程序参数，随同 “– javaagent”一起传入。与 main 函数不同的是，
     * 这个参数是一个字符串而不是一个字符串数组，如果程序参数有多个，程序将自行解析这个字符串。
     * 2、instrumentation 是一个 java.lang.instrument.Instrumentation 的实例，由 JVM 自动传入。*
     * java.lang.instrument.Instrumentation 是 instrument 包中定义的一个接口，也是这个包的核心部分，
     * 集中了其中几乎所有的功能方法，例如类定义的转换和操作等等。
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("=========premain方法执行1========");
        System.out.println("装载成功 方法 premain 参数:" + agentArgs);
        System.out.println(agentArgs);

        //给UserServiceImpl 的getUser方法注入 打印时间戳的log
        String cName = "UserServiceImpl";

        //转化原始字节码文件
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if(className.replaceAll("/", ".").equals(cName)){
                    System.out.println("begin modify getUser  method");
                    ClassPool pool = new ClassPool();
                    pool.insertClassPath(new LoaderClassPath(loader));
                    try {
                        CtClass ctl = pool.get(cName);
                        CtMethod method = ctl.getDeclaredMethod("getUser");
                        method.insertBefore("System.out.println(System.currentTimeMillis());");
                        System.out.println("end modify getUser  method");
                        return ctl.toBytecode();
                    }catch (NotFoundException exception){
                        exception.printStackTrace();
                    } catch (CannotCompileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }

    /**
     * 如果不存在 premain(String agentArgs, Instrumentation inst)
     * 则会执行 premain(String agentArgs)
     * @param agentArgs
     */
    public static void premain(String agentArgs) {
        System.out.println("=========premain方法执行2========");
        System.out.println(agentArgs);
    }
}
