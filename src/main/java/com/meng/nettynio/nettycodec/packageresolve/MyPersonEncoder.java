package com.meng.nettynio.nettycodec.packageresolve;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder");
        int length = msg.getLength();
        byte[] content = msg.getContent();

        out.writeInt(length);
        out.writeBytes(content);
    }
}
