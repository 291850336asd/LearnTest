package com.meng.example.enums;

/**
 * 命令类型
 */
public enum CommandTypeEnum {
    hget, hgetALl, expire, expireAt, ttl,
    decrBy, desc, incrBy, incr, hset,
    set, get, exists, type, append, substr, hsetnx, hmset, hmget, hincrBy,
    hexists, hdel, hlen, hkeys, keys, dbSize, del, setex,

    rpush,lpush, llen, lrange, lindex, lpop, rpop, ltrim, lrem,

    sadd, srem, scard, smembers, smemberl
}
