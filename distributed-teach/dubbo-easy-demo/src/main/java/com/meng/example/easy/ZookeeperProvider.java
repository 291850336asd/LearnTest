package com.meng.example.easy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ZookeeperProvider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
               "config-easy/spring-dubbo-zk-provider.xml");
        context.start();
        System.out.println("dubbo 服务启动成功 ");
        System.in.read(); // press any key to exit
    }
}