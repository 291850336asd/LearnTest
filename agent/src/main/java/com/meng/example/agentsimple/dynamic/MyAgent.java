package com.meng.example.agentsimple.dynamic;

import com.meng.example.agentsimple.c3p0.C3p0Agent;

import java.lang.instrument.Instrumentation;


public class MyAgent {

    /**
     * @param args
     * @param agentmain
     */
    public static void agentmain(String args, Instrumentation agentmain) {
        System.out.println(String.format("系统载入agentmain 参数%s 载入方法:premain", args));
    }

    /**
     * agentsimple 静态入口
     *
     * @param args
     * @param inst
     */
    public static void premain(String args, Instrumentation inst) {
        System.out.println(String.format("系统载入myAgent 参数%s 载入方法:premain", args));
//        System.out.println("载入C3p0Agent");
        inst.addTransformer(new C3p0Agent());
    }


}
