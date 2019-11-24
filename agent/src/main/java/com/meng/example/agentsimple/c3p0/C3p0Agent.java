package com.meng.example.agentsimple.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import javassist.*;
import org.junit.Assert;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.InetSocketAddress;
import java.security.ProtectionDomain;
import java.util.concurrent.Executors;

public class C3p0Agent implements ClassFileTransformer {


    static String targetClass = "com.mchange.v2.c3p0.ComboPooledDataSource";

    public  C3p0Agent() {
        try {
            openHttpServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openHttpServer() throws IOException {
        InetSocketAddress addr = new InetSocketAddress(5555);
        HttpServer server = HttpServer.create(addr, 0);
        server.createContext("/server", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type", "text/plain;charset=UTF-8");
                exchange.sendResponseHeaders(200, 0);
                OutputStream responseBody = exchange.getResponseBody();
                // 输出c3p0状态
                responseBody.write(C3p0Agent.this.getStatus().getBytes());
                responseBody.flush();
                responseBody.close();
            }
        });
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server is listening on port 5555");
    }

    public String getStatus() {
        Object source2 = System.getProperties().get("c3p0Source$agentsimple");
        if (source2 == null) {
            return "未初始任何c3p0数据源";
        }
        return source2.toString();
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] result = null;
        ClassPool  pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(loader));
        if (className != null && className.replace("/", ".").equals(targetClass)) {
            try {
                CtClass ctl = pool.get(targetClass);
                ctl.getConstructor("()V")
                        .insertAfter("System.getProperties().put(\"c3p0Source$agentsimple\", $0);");
                result = ctl.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(className.replace("/", ".").equals("com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource")){
          try {
              System.out.println(className);
              CtClass ctl = pool.get(className.replace("/", "."));
              CtMethod method = ctl.getDeclaredMethod("getConnection");
              CtMethod agentMethod = CtNewMethod.copy(method, method.getName()+"$agent", ctl, null);
              ctl.addMethod(agentMethod);
              String newSrc = "{ Object result = ($w)" + method.getName() + "$agent($$);" +
                      "com.meng.example.agentsimple.c3p0.C3p0ConnectProxy proxy = " +
                      "new com.meng.example.agentsimple.c3p0.C3p0ConnectProxy(result);" +
                      "return ($r) proxy.getProxy(); }";
              method.setBody(newSrc);
              result = ctl.toBytecode();
          }catch (Exception e){
              e.printStackTrace();
          }

        }
        return result;
    }

    // 测试插桩
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new LoaderClassPath(C3p0Agent.class.getClassLoader()));
        CtClass ctl = pool.get(targetClass);
        ctl.getConstructor("()V")
                .insertAfter("System.getProperties().put(\"c3p0Source$agentsimple\", $0);");
        ctl.toClass();
        ComboPooledDataSource source = new ComboPooledDataSource("mysql");
        Object source2 = System.getProperties().get("c3p0Source$agentsimple");
        System.out.println(source.toString());
        Assert.assertEquals(source, source2);
    }
}
