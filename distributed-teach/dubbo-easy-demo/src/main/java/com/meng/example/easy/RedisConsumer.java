package com.meng.example.easy;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;

public class RedisConsumer {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "config-easy/spring-dubbo-redis-consumer.xml");
        context.start();
        System.out.println("消费者启动");
        DemoService demoService = (DemoService) context.getBean("demoService");
        String str = demoService.sayHello("meng");
        System.out.println(str);
        String str1 = demoService.sayHello2("meng");
        System.out.println(str1);
        str1 = demoService.sayHello();
        System.out.println(str1);
        System.in.read();

    }

}
