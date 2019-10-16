package com.meng.nettynio.nettymillon;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.Collection;

public class MyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

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
                                ch.pipeline().addLast(new MyConnectCountHandler());
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
