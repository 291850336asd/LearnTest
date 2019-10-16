package com.meng.nettynio.nettyexample.thridMutiClientSocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    protected void channelRead0(ChannelHandlerContext ctx, final String msg) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("server recived : " + msg);
        channelGroup.forEach(item -> {
            if(channel != item){
                item.writeAndFlush(channel.remoteAddress() + " " + msg + "\n");
            }
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println(ctx.channel().remoteAddress() +   "  上线了  channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println(ctx.channel().remoteAddress() +   "  下线了  channelInactive");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        Channel channel = ctx.channel();
        //netty会自动调用
        channelGroup.remove(channel);
        channelGroup.writeAndFlush("服务器 -- " + channel.remoteAddress() + " 离开了\n");
        System.out.println("handlerRemoved");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("服务器 -- " + channel.remoteAddress() + " 加入了\n");
        channelGroup.add(channel);
        System.out.println("handlerAdded");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("channelUnregistered");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("exceptionCaught");
        ctx.channel().close();
    }
}
