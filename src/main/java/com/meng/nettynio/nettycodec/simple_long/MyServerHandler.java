package com.meng.nettynio.nettycodec.simple_long;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<Long> {
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("server received : " + msg);
        ctx.channel().writeAndFlush((msg + 1));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
