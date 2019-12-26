package com.meng.exapmle.rpccustom.client;

import com.meng.exapmle.rpccustom.User;
import com.meng.exapmle.rpccustom.UserService;

public class Client {

    public static void main(String[] args) {
        RpcClientProxy clientProxy = new RpcClientProxy();
        UserService userService = clientProxy.clientProxy(UserService.class,"localhost", 8888);
        String result = userService.sayHello("meng");
        System.out.println(result);
        result = userService.saveUser(new User("meng"));
        System.out.println(result);
    }
}
