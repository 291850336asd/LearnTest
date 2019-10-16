package com.meng.nettynio.nettycodec.messge_message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class StringToLongOutboundHandler extends MessageToMessageEncoder<String> {


    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        System.out.println("LongToByteOutboundHandler");
        out.add(Long.parseLong(msg));
    }
}
