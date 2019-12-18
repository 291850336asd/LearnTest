package com.meng.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/spring-dubbo-consumer.xml");
        context.start();
        System.out.println("消费者启动");
        DemoService demoService = (DemoService) context.getBean("demoService");
        String str = demoService.sayHello("meng");
        System.out.println(str);

        while (true) {
            byte[] b = new byte[1024];
            int szie = System.in.read(b);
            if (new String(b, 0, szie).trim().equals("send")) {
                System.out.println(demoService.sayHello("meng"));
            }
        }


    }

}
