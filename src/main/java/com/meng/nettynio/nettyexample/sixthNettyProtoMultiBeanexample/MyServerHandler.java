package com.meng.nettynio.nettyexample.sixthNettyProtoMultiBeanexample;

import com.meng.nettynio.nettyexample.protobuf.AllInfoProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<AllInfoProto.AllBeanMapper> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AllInfoProto.AllBeanMapper msg) throws Exception {
        if(msg.getDateType() == AllInfoProto.AllBeanMapper.DataType.CatType){
            AllInfoProto.Cat cat = msg.getCat();
            System.out.println(cat);
        } else if(msg.getDateType() == AllInfoProto.AllBeanMapper.DataType.DogType){
            AllInfoProto.Dog dog = msg.getDog();
            System.out.println(dog);
        } else {
            AllInfoProto.Student student = msg.getStudent();
            System.out.println(student);
        }
    }
}
