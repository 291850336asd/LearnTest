package com.meng.nettynio.nettycodec.messge_message;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline =  ch.pipeline();
        channelPipeline.addLast(new ReplayingByteToLongInboundHandler());
        channelPipeline.addLast(new LongToStringInboundHandler());
        channelPipeline.addLast(new LongToByteOutboundHandler());
        channelPipeline.addLast(new StringToLongOutboundHandler());
        channelPipeline.addLast(new MyClientHandler());
    }

}
