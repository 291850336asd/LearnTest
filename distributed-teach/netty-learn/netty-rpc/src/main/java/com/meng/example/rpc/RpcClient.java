package com.meng.example.rpc;

import com.meng.example.rpc.client.ClientChannel;
import com.meng.example.rpc.client.ClientDecoder;
import com.meng.example.rpc.client.ClientEncoder;
import com.meng.example.rpc.client.ClientHandler;
import com.meng.example.rpc.codec.Codec;
import com.meng.example.rpc.codec.CodecFactory;
import com.meng.example.rpc.protocol.ProtocolRequestEntity;
import com.meng.example.rpc.protocol.ProtocolResponseEntity;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcClient implements Closeable {

    private String address;

    private int port;

    private ChannelFuture future;

    private EventLoopGroup group;

    private RpcClient(String address, int port) throws InterruptedException {
        this.address = address;
        this.port = port;

        //打开远程连接
        group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(ClientChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline cp = socketChannel.pipeline();
                        //write
                        cp.addFirst("write", new ClientEncoder());
                        //read
                        cp.addLast("read", new ClientDecoder());
                        cp.addLast("read-handler",  new ClientHandler());
                    }
                });
        this.future = bootstrap.connect(address, port).sync();
        System.out.println("rpc client start");
    }

    public static RpcClient get(String address, int port) throws InterruptedException {
        return new RpcClient(address, port);
    }

    public <T> T getProxy(Class<T> clazz) {
        T proxy = (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new RpcInvoker(this.future));
        return proxy;
    }

    @Override
    public void close() {
        //关闭远程连接
//        try {
//            this.future.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        this.future.channel().close();
        this.group.shutdownGracefully();

        System.out.println("rpc client close");
    }

    static class RpcInvoker implements InvocationHandler {

        private Codec codec = CodecFactory.codec();

        private ChannelFuture future;

        public RpcInvoker(ChannelFuture future) {
            this.future = future;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Class:" + method.getDeclaringClass().toString() + "\tmethod:" + method.getName());
            if (Object.class == method.getDeclaringClass()) {
                return method.invoke(this, args);
            }
            System.out.println("remote invoke Class:" + method.getDeclaringClass().toString() + "\tmethod:" + method.getName());

            Object result = null;

            //拼装请求包体
            ProtocolRequestEntity entity = new ProtocolRequestEntity();
            entity.setClazzName(method.getDeclaringClass().toString());
            entity.setMethodName(method.getName());
            if (args != null) {
                entity.setParams(codec.encode(args));
            }

            //执行远程函数
            ClientChannel channel = (ClientChannel)this.future.channel();
            channel.reset();
            ChannelFuture responseFuture = channel.writeAndFlush(entity);
            //获取远程执行结果
            ProtocolResponseEntity responsetEntity = channel.get(5000000);

            //反序列化结果，需要对返回类型做特殊处理，例如集合等等
            byte[] result_bytes = responsetEntity.getResult();
            Class returnClazz = method.getReturnType();
            if (returnClazz.isArray()) {
                System.out.println("返回值是个集合：" + returnClazz.getName());
            }
//            if (returnClazz.getComponentType() == null) {
                result = codec.decode(result_bytes, returnClazz);
//            } else {
//                result = codec.decodeArray(result_bytes, returnClazz);
//            }
            if (result.getClass().isArray()) {
                System.out.println("返回值是个集合：" + returnClazz.getName());
            }
            return result;
        }
    }

}
