package com.meng.nettynio.nettycodec.messge_message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class LongToStringInboundHandler extends MessageToMessageDecoder<Long>{
    @Override
    protected void decode(ChannelHandlerContext ctx, Long msg, List<Object> out) throws Exception {
        System.out.println("LongToStringInboundHandler");
        out.add(String.valueOf(msg));
    }
}
