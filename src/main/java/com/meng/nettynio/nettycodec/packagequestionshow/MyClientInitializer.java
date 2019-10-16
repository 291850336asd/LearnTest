package com.meng.nettynio.nettycodec.packagequestionshow;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline =  ch.pipeline();
        channelPipeline.addLast(new MyClientHandler());
    }

}
