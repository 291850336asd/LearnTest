package com.meng.apm.service.monitor;

import com.meng.apm.service.monitor.collects.JdbcCommonCollects;
import com.meng.apm.service.monitor.collects.SpringControllerCollects;
import com.meng.apm.service.monitor.collects.SpringServiceCollects;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AgentMain implements ClassFileTransformer {
    protected static AgentMain agentMain;
    private static Collect[] collects; // 采集器集合
    private Map<ClassLoader, ClassPool> classPoolMap = new ConcurrentHashMap<ClassLoader, ClassPool>();
    private static String packagePath = null;

    // 上传地址
    // 参数:
    // pro.key=
    // 访问远程服务 获取属性配置
    public static void agentmain(String args, Instrumentation inst) {
    	
    }

    private static final ArrayList<String> keys;

    static {
        String paramKesy[] = {"server", "key", "secret"};
        keys = new ArrayList<String>();
        keys.addAll(Arrays.asList(paramKesy));
    }

    // 在应用启动前调用
    public static void premain(String agentArgs, Instrumentation inst) {
        packagePath = agentArgs;
        if (agentArgs != null) {
            String[] paramGroup = agentArgs.split(",");
            for (String param : paramGroup) {
                String[] keyValue = param.split("=");
                if (keys.contains(keyValue[0])) {
                    System.setProperty("$bit_" + keyValue[0], keyValue[1]);
                }
            }
        }
        // 验主验置
//        if (System.getProperty("$bit_server") == null) {
//            System.setProperty("$bit_server", "http://api.ibitedu.com/receive");
//        }
//        Assert.checkNull(System.getProperty("$bit_key"),"$bit_key key is not null");
//        Assert.checkNull(System.getProperty("$bit_secret"),"$bit_secret key is not null");


        collects = new Collect[]{

         		SpringServiceCollects.INSTANCE,
                SpringControllerCollects.INSTANCE,
                JdbcCommonCollects.INSTANCE
        };
        agentMain = new AgentMain();
        inst.addTransformer(agentMain);
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (className == null || loader == null
                || loader.getClass().getName().equals("sun.reflect.DelegatingClassLoader")
                || loader.getClass().getName().equals("org.apache.catalina.loader.StandardClassLoader")
                || loader.getClass().getName().equals("javax.management.remote.rmi.NoCallStackClassLoader")
                || loader.getClass().getName().equals("com.alibaba.fastjson.util.ASMClassLoader")
                || className.indexOf("$Proxy") != -1
                || className.indexOf("Proxy") != -1
                || className.indexOf("iface") != -1
                || className.indexOf("$") != -1
                || className.indexOf("cmc/member/grade/admin/") == -1
//                || (className.indexOf("cmc/member/grade/admin/") == -1 && className.indexOf("NonRegisteringDriver") == -1)  error
                || className.startsWith("java")
                ) {
            return null;
        }
        System.out.println("code : " + className);
        if (!classPoolMap.containsKey(loader)) {
            ClassPool classPool = new ClassPool();
            classPool.insertClassPath(new LoaderClassPath(loader));
            classPoolMap.put(loader, classPool);
        }
        ClassPool cp = classPoolMap.get(loader);
        try {
            className = className.replaceAll("/", ".");
            CtClass cclass = cp.get(className);
            for (Collect c : collects) {
                if (c.isTarget(className, loader, cclass)) { // 仅限定只能转换一次.
                    //得到转换后的字节码
                    byte[] bytes = c.transform(loader, className, classfileBuffer, cclass);
                    System.out.println(String.format("%s bit APM agentsimple insert success", className));
                    return bytes;
                }
            }
        } catch (Throwable e) {
            new Exception(String.format("%s bit APM agentsimple insert fail", className), e).printStackTrace();
        }
        return new byte[0];
    }
}
