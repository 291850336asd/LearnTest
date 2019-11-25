package com.meng.example.agentadvance;


import java.lang.instrument.Instrumentation;
import java.util.Properties;

public class ApmAgent {

    public static void premain(String arg, Instrumentation instrumentation) {
        Properties properties = new Properties();
        //从arg中获取参数
        //扫描的包 service.include
        //需要移除的文件或者包  service.exclude
        // TODO
        //日志需要输出到哪里
        //日志增长策略
        //日志采样率
        properties.setProperty("xxx","arg");
        new ApmContext(properties, instrumentation);
    }

}
