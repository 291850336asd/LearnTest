package com.meng.example.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;

public interface IRedisConnection extends InitializingBean, DisposableBean {

    /**
     * 获取jedis客户端
     * @return
     */
    Jedis getJedis();

    /**
     * 获取业务key
     * @return
     */
    String getBusiness();

}
