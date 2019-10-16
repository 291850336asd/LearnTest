package com.meng.nettynio.nettycodec.packageresolve;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline =  ch.pipeline();
        channelPipeline.addLast(new MyPersonEncoder());
        channelPipeline.addLast(new MyPersonDecoder());
        channelPipeline.addLast(new MyClientPersonHandler());
    }

}
