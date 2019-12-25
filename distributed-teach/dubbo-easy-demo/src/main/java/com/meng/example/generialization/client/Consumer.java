package com.meng.example.generialization.client;

import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 泛化接口调用方式主要用于客户端没有 API 接口及模型类元的情况，
 * 参数及返回值中的所有 POJO 均用 Map 表示，通常用于框架集成，
 * 比如：实现一个通用的服务测试框架，可通过 GenericService 调用所有服务实现。
 */
public class Consumer {

    public static void main(String[] args) throws Exception {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-generialzation/client-consumer.xml");
        context.start();

        GenericService demoService = (GenericService) context.getBean("demoService");
        Object result = demoService.$invoke("sayHello1", new String[]{"java.lang.String"}, new Object[]{"world"});


        System.out.println(result);

    }
}