package com.meng.example.easy;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) throws BusException {
        return "Hello " + name;
    }

    @Override
    public String sayHello() throws BusException {
        return "ok";
    }

    @Override
    public String sayHello2(String name) {
        return "Hello2 " + name;
    }
}
