package com.meng.example.server;


import com.meng.example.rpc.RpcServer;
import com.meng.example.rpc.server.ServiceContainer;
import com.meng.example.server.imp.UserImp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RpcServerBootStart {

    public static void main(String[] args) throws Exception {
        start();
    }

    private static void start() throws IOException, IllegalAccessException, InstantiationException, InterruptedException {
        RpcServer rpcServer = new RpcServer(9000);
        rpcServer.register(UserImp.class);
        rpcServer.start();
        System.out.println("server boot start");

        System.in.read();
        System.out.println("server boot end");
        rpcServer.close();
    }

    private static void testMethod() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        ServiceContainer serviceContainer = new ServiceContainer(UserImp.class);
        Object result = serviceContainer.invoke("findById", 1);
        System.out.println(result);
    }

}
