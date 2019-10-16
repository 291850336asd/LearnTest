package com.meng.nettynio.nettycodec.packageresolve;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyServerPersonHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("received length : " + length);
        System.out.println("received info : " + new String(content, Charset.forName("utf-8")));
        System.out.println("received count : " + ++this.count);

        String responseMessage = "一句卧槽走天下";
        int responseLength = responseMessage.getBytes(Charset.forName("utf-8")).length;
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setContent(responseMessage.getBytes(Charset.forName("utf-8")));
        personProtocol.setLength(responseLength);
        ctx.writeAndFlush(personProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
