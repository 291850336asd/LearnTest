package com.meng.nettynio.nettymillon;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {
    public static void main(String[] args) {
        new MyClient().start(Constant.BeginPort, Constant.Port_N);
    }

    public void start(int beginPort, int portLength){
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        try {
            int index =0;
            while (!Thread.interrupted()){
                final Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_REUSEADDR, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {

                            }
                        });

                int port;

                port = beginPort + index;
                System.out.println(port);
                ChannelFuture channelFuture = bootstrap.connect("localhost", port);
                channelFuture.addListener((ChannelFutureListener)(future)->{
                   if(!future.isSuccess()){
                       System.out.println("connected failed -> exit");
                       System.exit(0);
                   }
                });
                try {
                    channelFuture.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(++index== portLength){
                    index = 0;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }
}
