package com.meng.example.redis.redis.custom.cluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestRedisClient {
    public static void main(String[] args) throws IOException {


        List<RedisClient> pool=new ArrayList<>();
        pool.add(new RedisClient("192.168.0.12",6379));
        pool.add(new RedisClient("192.168.0.12",6380));
        pool.add(new RedisClient("192.168.0.12",6381));
        Crc16Sharding crc16Sharding=new Crc16Sharding(pool);
        for (int i=0;i<100;i++){
            String key="xx"+i;

            RedisClient redisClient=crc16Sharding.crc16(key);
            redisClient.set(key,i+"");
            System.out.println(redisClient.get(key));;


        }




    }
}

