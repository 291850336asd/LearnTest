package com.meng.nettynio.nettycodec.packagequestionshow;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;

    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        System.out.println("receice : " + new String(buffer, Charset.forName("utf-8")));
        System.out.println("count : " + ++this.count);
        ByteBuf responseByteBuf = Unpooled.copiedBuffer(UUID.randomUUID().toString(),
                Charset.forName("utf-8"));
        ctx.channel().writeAndFlush(responseByteBuf);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
