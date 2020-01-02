package com.meng.exapmle.serial.protobuf.pb;

import com.google.protobuf.InvalidProtocolBufferException;
import com.meng.exapmle.serial.protobuf.javaraw.SubscribeReq;

import java.util.Arrays;

import static com.meng.exapmle.serial.protobuf.pb.SubscribeReqProto.SubscribeReq.newBuilder;

public class ProtobufSerial {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        byte[] result = serializeObject();
        System.out.println(Arrays.toString(result));

        //反序列化
        SubscribeReqProto.SubscribeReq req = deserializeObject(result);
        System.out.println(req.getSubReqID());
        System.out.println(req.getUserName());
        System.out.println(req.getProductName());
        System.out.println(req.getAddress(0));
        System.out.println(req.getAddress(1));
        System.out.println(req.getAddress(2));
    }

    private static byte[] serializeObject(){
        SubscribeReqProto.SubscribeReq.Builder builder = newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("abc");
        builder.setProductName("netty");
        builder.addAllAddress(Arrays.asList("长沙", "深圳", "北京"));
        return builder.build().toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq deserializeObject(byte[] bytes) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq result = SubscribeReqProto.SubscribeReq.parseFrom(bytes);
        return result;
    }
}
