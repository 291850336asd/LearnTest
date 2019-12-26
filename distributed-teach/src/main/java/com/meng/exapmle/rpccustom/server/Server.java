package com.meng.exapmle.rpccustom.server;

import com.meng.exapmle.rpccustom.UserService;

public class Server {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        RpcServerProxy rpcServerProxy = new RpcServerProxy();
        rpcServerProxy.publisher(userService, 8888);
    }
}
