package com.meng.nettynio.nettyexample.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client remote address : " +  ctx.channel().remoteAddress());
        System.out.println("client local address : " +  ctx.channel().localAddress());
        ctx.channel().writeAndFlush("from server : " + msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ok");
    }
}
