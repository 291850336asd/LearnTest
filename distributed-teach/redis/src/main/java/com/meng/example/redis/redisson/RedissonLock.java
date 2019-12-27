package com.meng.example.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonLock {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.163.128:6379").setDatabase(0);
        Redisson redisson = (Redisson) Redisson.create(config);
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockinfo(redisson);
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lockinfo(redisson);
//            }
//        }).start();

    }

    private static void lockinfo(Redisson redisson){
        RLock rLock = redisson.getLock("key");

        try {
            boolean isLock = rLock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(isLock + "  begin");
            if(isLock){
                Thread.sleep(20000);
                System.out.println(isLock);
            }
            System.out.println("finished");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rLock.unlock();
        }
    }
}
