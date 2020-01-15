package com.meng.example;

import com.meng.example.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/mybatisShardingDatabaseAndTableContext.xml");
        DemoService demoService = applicationContext.getBean(DemoService.class);
        demoService.demo();
    }
}
