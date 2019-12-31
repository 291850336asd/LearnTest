package com.meng.example.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisCluster;

public interface IRedisClusterConnection extends InitializingBean, DisposableBean {

    JedisCluster getJedisCluster();

    String getBusiness();

}
