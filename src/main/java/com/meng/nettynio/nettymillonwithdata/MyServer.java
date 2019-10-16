package com.meng.nettynio.nettymillonwithdata;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.Collection;

public class MyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroupHandler = new NioEventLoopGroup(1000);
        try{
            Collection<Channel> channels = new ArrayList<>(Constant.Port_N);
            for (int i = 0; i < Constant.Port_N; i++) {
                int realPort = Constant.BeginPort + i;
                ServerBootstrap serverBootstrap = new ServerBootstrap();
                serverBootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childOption(ChannelOption.SO_REUSEADDR, true)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline channelPipeline =  ch.pipeline();
                                channelPipeline.addLast(
                                        new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                                                0, 4,
                                                0, 4))
                                        .addLast(new LengthFieldPrepender(4))
                                        .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                        .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                        .addLast(workerGroupHandler,new MyConnectCountHandler());
                            }
                        });
                Channel serverChannel =serverBootstrap.bind(realPort).addListener((ChannelFutureListener)future->{
                    if(future.isSuccess())
                    System.out.println("bind port success port : " + realPort);
                }).sync().channel();
                channels.add(serverChannel);
            }
            for (Channel ch : channels) {
                ch.closeFuture().sync();
            }
            System.out.println("server start");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
