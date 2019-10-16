package com.meng.redis.redis.redisprotolog;

import redis.clients.jedis.Jedis;

import java.util.Collections;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.163.128", 6379);
        jedis.set("meng","info");
        jedis.expire("meng", 10);
    }

}