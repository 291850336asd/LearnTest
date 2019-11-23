package com.meng.example.agentsimple.c3p0;

import com.mchange.v2.c3p0.impl.NewProxyStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class C3p0ConnectProxy {

    private Connection connection;

    public C3p0ConnectProxy(Object connection){
        this.connection = (Connection) connection;
    }


    public Connection getProxy(){

        return (Connection) Proxy.newProxyInstance(C3p0ConnectProxy.class.getClassLoader(),
                connection.getClass().getInterfaces(),
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invokeResult = method.invoke(connection, args);
               try {
                   if(method.getReturnType() == Statement.class ||
                           method.getReturnType()== PreparedStatement.class ||
                           method.getReturnType() == NewProxyStatement.class) {
                       C3p0StatementProxy statementProxy = new C3p0StatementProxy(invokeResult);
                       return statementProxy.getProxy();
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }

                return invokeResult;
            }
        });
    }

}
