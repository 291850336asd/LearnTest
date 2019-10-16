package com.meng.nettynio.nettycodec.simple_long;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("client received : " + msg);
       // ctx.channel().writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().writeAndFlush(27384738L);
//        ctx.channel().writeAndFlush(2L);
//        ctx.channel().writeAndFlush(3L);
//        ctx.channel().writeAndFlush(4L);
//        ctx.channel().writeAndFlush(5L);
     //   ctx.channel().writeAndFlush("hello world");
//        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hello world 2", Charset.forName("utf-8")));
    }
}
