package com.meng.exapmle.rpccustom.server;

import com.meng.exapmle.rpccustom.User;
import com.meng.exapmle.rpccustom.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public String saveUser(User user) {
        return "ok";
    }
}
