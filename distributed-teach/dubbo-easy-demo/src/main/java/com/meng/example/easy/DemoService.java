package com.meng.example.easy;

public interface DemoService {
    String sayHello(String name) throws BusException;
    String sayHello() throws BusException;
    String sayHello2(String name);
}