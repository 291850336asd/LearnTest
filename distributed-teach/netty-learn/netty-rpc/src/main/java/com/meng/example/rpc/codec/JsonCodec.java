package com.meng.example.rpc.codec;

import com.alibaba.fastjson.JSON;

import java.nio.charset.StandardCharsets;

public class JsonCodec implements Codec {

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONString(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T decode(byte[] obj, Class<T> clazz) {
        return JSON.parseObject(new String(obj, StandardCharsets.UTF_8), clazz);
    }

    @Override
    public Object decode(String obj, Class clazz) {
        return JSON.parseObject(obj, clazz);
    }

    @Override
    public Object decodeArray(byte[] obj, Class clazz) {
        return JSON.parseArray(new String(obj), clazz.getComponentType());
    }

}
