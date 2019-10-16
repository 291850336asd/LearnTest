package com.meng.nettynio.nettycodec.simple_long;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class LongToByteOutboundHandler extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("LongToByteOutboundHandler");
        out.writeLong(msg);
    }
}
