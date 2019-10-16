package com.meng.nettynio.nettycodec.simple_long;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline =  ch.pipeline();
         channelPipeline.addLast(new ByteToLongInboundHandler());
        channelPipeline.addLast(new LongToByteOutboundHandler());
        channelPipeline.addLast(new MyServerHandler());
    }
}