package com.meng.nettynio.nettyexample.fourheartBeat;

import com.meng.nettynio.nettyexample.thridMutiClientSocket.MyChatClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyClient {

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
//            channelFuture.channel().closeFuture().sync();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                channelFuture.channel().writeAndFlush(br.readLine() + "\n");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
