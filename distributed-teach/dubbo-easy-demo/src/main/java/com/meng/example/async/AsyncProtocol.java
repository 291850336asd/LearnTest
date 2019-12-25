package com.meng.example.async;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class AsyncProtocol {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "async-provider.xml", AsyncProtocol.class);
        context.start();
        System.out.println("dubbo async Demo 服务启动成功 ");
        System.in.read(); // press any key to exit
    }

}
