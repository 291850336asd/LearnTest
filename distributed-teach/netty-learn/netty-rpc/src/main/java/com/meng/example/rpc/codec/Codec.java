package com.meng.example.rpc.codec;


public interface Codec {

    public byte[] encode(Object obj);

    public <T> T decode(byte[] obj, Class<T> clazz);

    public Object decode(String obj, Class clazz);

    public Object decodeArray(byte[] obj, Class clazz);

}
