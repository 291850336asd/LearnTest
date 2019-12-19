package com.meng.example.easy;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RedisProvider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "config-easy/spring-dubbo-redis-provider.xml"});
        context.start();
        System.out.println("dubbo multicast 服务启动");
        System.in.read();
    }

}
