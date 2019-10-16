package com.meng.nettynio.nettycodec.simple_long;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ByteToLongInboundHandler extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ByteToLongInboundHandler");
        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }

    }

}
