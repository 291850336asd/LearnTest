package com.meng.example.agentsimple.c3p0;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Statement;

public class C3p0StatementProxy {

    private Statement statement;
    
    public C3p0StatementProxy(Object invokeResult) {
        this.statement = (Statement) invokeResult;
    }

    public Statement getProxy() {
        return (Statement) Proxy.newProxyInstance(C3p0StatementProxy.class.getClassLoader(), statement.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    if(method.getName().startsWith("execute")) {
                        System.out.println("C3p0StatementProxy正在执行 " + method.getDeclaringClass() + "." + method.getName());
                        if(null != args){
                            for (Object o : args){
                                System.out.println("参数 ：" + o);
                            }
                        }

                        long startTime = System.currentTimeMillis();
                        Object invokeResult = method.invoke(statement, args);
                        long endTime = System.currentTimeMillis();
                        System.out.println("C3p0StatementProxy 执行 时间 ：" + (endTime - startTime) + "ms");
                        return invokeResult;

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return  method.invoke(statement, args);
            }
        });
    }
}
