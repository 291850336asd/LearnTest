package com.meng.nettynio.nettyexample.sixthNettyProtoSingleBeanexample;

import com.meng.nettynio.nettyexample.protobuf.DataInfoProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<DataInfoProto.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfoProto.Student msg) throws Exception {
        System.out.println(msg);
    }
}
