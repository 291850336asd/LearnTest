package com.meng.spring.test;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void createBeanTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        UserServiceImp bean = factory.createBean(UserServiceImp.class);
        System.out.println(bean);
    }
    @org.junit.Test
    public void beanSaveTest() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("bean",new UserServiceImp());
        System.out.println(factory.getBean("bean"));
    }
    @org.junit.Test
    public void dependcsTest(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("userDao",new UserDaoImp());
        UserServiceImp userServiceImp = (UserServiceImp) factory.createBean(UserServiceImp.class,
                AbstractBeanDefinition.AUTOWIRE_BY_TYPE, true);
        System.out.println(userServiceImp);
    }

    @org.junit.Test
    public void getBeanTest(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("xxxx.xml");
        factory.getBean(UserServiceImp.class);
    }

    @org.junit.Test
    public void applocationContextTest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
       // context.start();
        context.getBean(UserServiceImp.class);

        //1.加载资源xml
        //2.先创建beanfactory、解析xml文件至beanDefinition并注册到beanfactory
        //3.对beanfactory进行初始化,对bean(singletion、非lazyini、非抽象bean)进行创建
    }

}
