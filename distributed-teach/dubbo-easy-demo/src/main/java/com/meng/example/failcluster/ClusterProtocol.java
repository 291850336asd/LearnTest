package com.meng.example.failcluster;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class ClusterProtocol {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "cluster-provider.xml", ClusterProtocol.class);
        context.start();
        System.out.println("dubbo cluster Demo 服务启动成功 ");
        System.in.read(); // press any key to exit
    }

}
