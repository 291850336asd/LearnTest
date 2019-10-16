package com.meng.apm.javaagent.simple;

import java.lang.instrument.Instrumentation;


/**
 * jdk1.5以后引入了javaAgent技术，javaAgent是运行方法之前的拦截器。
 * 我们利用javaAgent和ASM字节码技术，在JVM加载class二进制文件的时候，利用ASM动态的修改加载的class文件
 *
 * 如果你把 -javaagent 放在 -jar 后面，则不会生效。也就是说，放在主程序后面的 agent 是无效的。
 *
 *
 * 测试
 * -javaagent:E:\workspace\netty\out\artifacts\nettyagent_jar\netty.jar=Hello
 * -javaagent:E:\workspace\netty\out\artifacts\nettyagent_jar\netty.jar=World
 *
 * 参考 https://www.cnblogs.com/rickiyang/p/11368932.html
 */
public class TestAgent {

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * 并被同一个System ClassLoader装载
     * 被统一的安全策略(security policy)和上下文(context)管理
     *
     * 在这个 premain 函数中，开发者可以进行对类的各种操作。
     * 1、agentArgs 是 premain 函数得到的程序参数，随同 “– javaagent”一起传入。与 main 函数不同的是，
     * 这个参数是一个字符串而不是一个字符串数组，如果程序参数有多个，程序将自行解析这个字符串。
     * 2、Inst 是一个 java.lang.instrument.Instrumentation 的实例，由 JVM 自动传入。*
     * java.lang.instrument.Instrumentation 是 instrument 包中定义的一个接口，也是这个包的核心部分，
     * 集中了其中几乎所有的功能方法，例如类定义的转换和操作等等。
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("=========premain方法执行1========");
        System.out.println(agentArgs);
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
