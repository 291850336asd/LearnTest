package com.meng.nettynio.nettycodec.messge_message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class ReplayingByteToLongInboundHandler extends ReplayingDecoder<Void> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        System.out.println("ReplayingByteToLongInboundHandler");
        out.add(buf.readLong());
    }
}
