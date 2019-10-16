package com.meng.nettynio.nettyexample.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufferTest {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfoProto.Student student = DataInfoProto.Student.newBuilder().setName("张三").setAge(20).setAddress("北京").build();
        byte[] infos =  student.toByteArray();
        DataInfoProto.Student student2 = DataInfoProto.Student.parseFrom(infos);
        System.out.println(student2);
        System.out.println(student2.getAddress());
    }
}
