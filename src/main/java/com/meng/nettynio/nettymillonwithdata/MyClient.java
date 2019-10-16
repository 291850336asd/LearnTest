package com.meng.nettynio.nettymillonwithdata;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

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
                                ChannelPipeline channelPipeline =  ch.pipeline();
                                channelPipeline.addLast(
                                        new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                                                0, 4,
                                                0, 4))
                                        .addLast(new LengthFieldPrepender(4))
                                        .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                        .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                        .addLast(new MyClientHandler());
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
                    channelFuture.sync();
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
