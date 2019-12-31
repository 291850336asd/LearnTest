package com.meng.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-redis-monitor.xml"})
public class AppTest 
{
    @Autowired
    IRedis jedis;

    @Test
    public void testRedisMonitor() throws InterruptedException {
        String business="hotel";
        System.out.println("判断某个键是否存在："+jedis.exists(business,"username"));
        System.out.println("新增<'username','wukong'>的键值对："+jedis.set(business,"username", "wukong"));
        System.out.println("是否存在:"+jedis.exists(business,"name"));
        System.out.println("新增<'password','password'>的键值对："+jedis.set(business,"password", "password"));
        //Set<String> keys = jedis.keys("*");
        // System.out.println("系统中所有的键如下："+keys);
        System.out.println("删除键password:"+jedis.del("password"));
        System.out.println("判断键password是否存在："+jedis.exists(business,"password"));
        System.out.println("设置键username的过期时间为5s:"+jedis.expire(business,"username", 5));
        TimeUnit.SECONDS.sleep(2);
        System.out.println("查看键username的剩余生存时间："+jedis.ttl(business,"username"));
        //System.out.println("移除键username的生存时间："+jedis.persist("username"));
        System.out.println("查看键username的剩余生存时间："+jedis.ttl(business,"username"));
        System.out.println("查看键username所存储的值的类型："+jedis.type(business,"username"));
        Thread.sleep(Long.MAX_VALUE);
    }
}
