package com.meng.apm.service.monitor.collects;


import com.meng.apm.service.monitor.AbstractCollects;
import com.meng.apm.service.monitor.AgentLoader;
import com.meng.apm.service.monitor.Collect;
import com.meng.apm.service.monitor.NotProguard;
import javassist.*;

/**
 * spring 注解服务收集
 */
@NotProguard
public class SpringServiceCollects extends AbstractCollects implements Collect {
    @NotProguard
    public static SpringServiceCollects INSTANCE = new SpringServiceCollects();

    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    static {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("com.meng.apm.service.monitor.collects.SpringServiceCollects instance= ");
        sbuilder.append("com.meng.apm.service.monitor.collects.SpringServiceCollects.INSTANCE;\r\n");
        sbuilder.append("com.meng.apm.service.monitor.AbstractCollects.Statistics statistic =instance.begin(\"%s\",\"%s\");");
        beginSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.end(statistic);");
        endSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.error(statistic,e);");
        errorSrc = sbuilder.toString();
    }

    @Override
    public boolean isTarget(String className, ClassLoader loader, CtClass ctclass) {
        try {
            for (Object obj : ctclass.getAnnotations()) {
                if (obj.toString().startsWith("@org.springframework.stereotype.Service")) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println(String.format("bit apm run error targetClassName=%s errorMessage=%s",className,e.getClass().getSimpleName()+":"+e.getMessage()));
        }
        return false;
    }

    @NotProguard
    @Override
    public Statistics begin(String className, String method) {
        ServiceStatistics serviceStatistics = new ServiceStatistics(super.begin(className, method));
        serviceStatistics.serviceName = className;
        serviceStatistics.methodName = method;
        serviceStatistics.logType="service";
        return serviceStatistics;
    }

    @Override
    public void sendStatistics(Statistics stat) {
        sendStatisticByHttp(stat,"serviceLog");
    }

    public byte[] transform(ClassLoader loader, String className, byte[] classfileBuffer, CtClass ctclass) throws
            Exception {
        AgentLoader byteLoade = new AgentLoader(className, loader, ctclass);
        CtMethod[] methods = ctclass.getDeclaredMethods();
        for (CtMethod m : methods) {
            // 屏蔽非公共方法
            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }
            // 屏蔽静态方法
            if (Modifier.isStatic(m.getModifiers())) {
                continue;
            }
            // 屏蔽本地方法
            if (Modifier.isNative(m.getModifiers())) {
                continue;
            }

            AgentLoader.MethodSrcBuild build = new AgentLoader.MethodSrcBuild();
            build.setBeginSrc(String.format(beginSrc,className,m.getName()));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);
            byteLoade.updateMethod(m, build);
        }
        return byteLoade.toBytecote();
    }

    @NotProguard
    public static class ServiceStatistics extends Statistics {
        public String serviceName; //服务名称
        public String methodName;// 方法名称
        public ServiceStatistics(Statistics s) {
            super(s);
        }
    }
}
