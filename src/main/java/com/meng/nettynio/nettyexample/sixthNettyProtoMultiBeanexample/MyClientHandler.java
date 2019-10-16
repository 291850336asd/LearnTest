package com.meng.nettynio.nettyexample.sixthNettyProtoMultiBeanexample;

import com.meng.nettynio.nettyexample.protobuf.AllInfoProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class MyClientHandler extends SimpleChannelInboundHandler<AllInfoProto.AllBeanMapper> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AllInfoProto.AllBeanMapper msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        AllInfoProto.AllBeanMapper allBeanMapper = null;
        int randomInt = new Random().nextInt(4);
        if(randomInt == 4){
            allBeanMapper = AllInfoProto.AllBeanMapper.newBuilder()
                    .setCat(AllInfoProto.Cat.newBuilder().setCity("北京").setName("cat").build())
                    .setDateType(AllInfoProto.AllBeanMapper.DataType.CatType).build();
        } else if(randomInt == 3){
            allBeanMapper = AllInfoProto.AllBeanMapper.newBuilder()
                    .setDog(AllInfoProto.Dog.newBuilder().setName("dog").build())
                    .setDateType(AllInfoProto.AllBeanMapper.DataType.DogType).build();
        } else {
            allBeanMapper = AllInfoProto.AllBeanMapper.newBuilder()
                    .setStudent(AllInfoProto.Student.newBuilder().setName("student").setAge(21).setAddress("bbbb").build())
                    .setDateType(AllInfoProto.AllBeanMapper.DataType.StudentType).build();
        }
        ctx.channel().writeAndFlush(allBeanMapper);

    }
}
