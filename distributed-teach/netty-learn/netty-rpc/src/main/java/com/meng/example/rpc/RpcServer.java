package com.meng.example.rpc;

import com.meng.example.rpc.server.ServerDecoder;
import com.meng.example.rpc.server.ServerEncoder;
import com.meng.example.rpc.server.ServerHandler;
import com.meng.example.rpc.server.ServiceContainer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RpcServer implements Closeable {

    private int port;

    private Map<String, ServiceContainer> rpcServers;

    private ChannelFuture future;

    public RpcServer(int port) {
        this.port = port;
        this.rpcServers = new ConcurrentHashMap<>();
    }

    public void register(Class clazz) throws InstantiationException, IllegalAccessException {
        //获取类继承的接口
        Class[] intfs = clazz.getInterfaces();
        if (intfs.length == 0) {
            return;
        }
        String intfName = intfs[0].getCanonicalName();
        ServiceContainer serviceContainer = new ServiceContainer(clazz);
        this.rpcServers.put(intfName, serviceContainer);
    }

    public void start() throws InterruptedException {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline cp = socketChannel.pipeline();
                        //read
                        cp.addLast("read", new ServerDecoder());
                        cp.addLast("read-handler", new ServerHandler(rpcServers));
                        //write
                        cp.addFirst("write", new ServerEncoder());
                    }
                });

        // 绑定端口，同步等待成功
        this.future =b.bind("localhost", this.port).sync();
    }

    @Override
    public void close() throws IOException {
        try {
            this.future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("rpc server stop");
    }

}
