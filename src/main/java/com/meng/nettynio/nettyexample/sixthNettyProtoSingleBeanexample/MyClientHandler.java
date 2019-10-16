package com.meng.nettynio.nettyexample.sixthNettyProtoSingleBeanexample;

import com.meng.nettynio.nettyexample.protobuf.DataInfoProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<DataInfoProto.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfoProto.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfoProto.Student student = DataInfoProto.Student.newBuilder().setAddress("北京").setAge(20).setName("张三").build();
        ctx.channel().writeAndFlush(student);

    }
}
