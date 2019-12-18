package com.meng.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "config/spring-dubbo-provider.xml"});
        context.start();
        System.out.println("dubbo multicast 服务启动");
        System.in.read();
    }

}
