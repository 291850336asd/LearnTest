package com.meng.nettynio.nettycodec.messge_message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server received : " + msg);
        ctx.channel().writeAndFlush((msg));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
