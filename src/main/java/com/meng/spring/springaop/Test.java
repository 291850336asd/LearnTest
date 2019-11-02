package com.meng.spring.springaop;

import com.meng.apm.service.monitor.json.JsonWriter;
import com.meng.spring.test.UserService;
import com.meng.spring.test.UserServiceImp;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.*;

public class Test {

    @org.junit.Test
    public void createProxyFactoryTest(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new UserServiceImp());
        CountingBeforeAdvice count = new CountingBeforeAdvice();
        proxyFactory.addAdvice(count);
        UserService userService = (UserService) proxyFactory.getProxy();
        userService.setName("haha");
        userService.getName();
        userService.getName();
        System.out.println(JsonWriter.objectToJson(count.map));
    }

    // 动态移除和添加通知
    @org.junit.Test
    public void createProxyFactoryTest2(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new UserServiceImp());
        UserService userService = (UserService) proxyFactory.getProxy();
        userService.setName("haha");

        Advised advised = (Advised) userService;
        CountingBeforeAdvice count = new CountingBeforeAdvice();
        advised.addAdvice(count);
        userService.getName();
        userService.getName();
        System.out.println(JsonWriter.objectToJson(count.map));
        advised.removeAdvice(count);
        userService.getName();
        userService.getName();
        System.out.println(JsonWriter.objectToJson(count.map));
    }



    @org.junit.Test
    public void createAspectJProxyFactoryTest(){
        AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory();
        aspectJProxyFactory.setTarget(new UserServiceImp());
        AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        CountingBeforeAdvice count = new CountingBeforeAdvice();
        aspectJExpressionPointcutAdvisor.setAdvice(count);
        aspectJExpressionPointcutAdvisor.setExpression("execution(* *.setName(..))");
        aspectJProxyFactory.addAdvisor(aspectJExpressionPointcutAdvisor);
        UserService userService =  aspectJProxyFactory.getProxy();
        userService.setName("haha");
        userService.getName();
        userService.getName();
        System.out.println(JsonWriter.objectToJson(count.map));
    }


    @org.junit.Test
    public void createAopProxyFactoryTest(){
        AopProxyFactory factory = new DefaultAopProxyFactory();
        AdvisedSupport config = new AdvisedSupport();
        CountingBeforeAdvice count = new CountingBeforeAdvice();
        UserServiceImp userServiceImp = new UserServiceImp();
        config.setTarget(userServiceImp);
        config.addAdvice(count);
        UserService userService = (UserServiceImp) factory.createAopProxy(config).getProxy();
        userService.setName("hahha");
        System.out.println(JsonWriter.objectToJson(count.map));
    }



}
