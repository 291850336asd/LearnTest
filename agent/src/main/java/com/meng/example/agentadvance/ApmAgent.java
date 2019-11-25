package com.meng.example.agentadvance;


import java.lang.instrument.Instrumentation;
import java.util.Properties;

public class ApmAgent {

    public static void premain(String arg, Instrumentation instrumentation) {
        Properties properties = new Properties();
        //从arg中获取参数
        properties.setProperty("xxx","arg");
        ApmContext context = new ApmContext(properties, instrumentation);
    }

}
