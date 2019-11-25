package com.meng.example.agentadvance;

import com.meng.example.agentadvance.collects.ServiceCollect;
import com.meng.example.agentadvance.filter.JSONFormat;
import com.meng.example.agentadvance.output.JulOutput;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ApmContext {

    private Instrumentation instrumentation;
    private Properties properties;
    List<ICollect> collects = new ArrayList();
    IFilter filter;
    IOutput output;

    public ApmContext(Properties properties, Instrumentation instrumentation) {
        this.properties = properties;
        this.instrumentation = instrumentation;
        collects.add(new ServiceCollect(this, instrumentation));
        filter = new JSONFormat();
        output = new JulOutput();
    }

    // 递交采集结果
    public void submitCollectResult(Object value) {
        // TODO 基于线程后台执行
        value = filter.doFilter(value);
        output.out(value);
    }

    public String getConfig(String key) {
        return properties.getProperty(key);
    }

    public List<ICollect> getCollects() {
        return collects;
    }

}
