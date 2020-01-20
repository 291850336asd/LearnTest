package com.meng.example.rpc.codec;


import com.thoughtworks.xstream.XStream;

import java.nio.charset.StandardCharsets;

public class XmlCodec implements Codec {

    private XStream xstream = new XStream();

    @Override
    public byte[] encode(Object obj) {
        return xstream.toXML(obj).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public <T> T decode(byte[] obj, Class<T> clazz) {
        return (T) xstream.fromXML(new String(obj, StandardCharsets.UTF_8));
    }

    @Override
    public Object decode(String obj, Class clazz) {
        return xstream.fromXML(obj);
    }

    @Override
    public Object decodeArray(byte[] obj, Class clazz) {
        return xstream.fromXML(new String(obj, StandardCharsets.UTF_8));
    }
}
