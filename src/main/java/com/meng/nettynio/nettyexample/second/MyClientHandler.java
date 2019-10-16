package com.meng.nettynio.nettyexample.second;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("remote address : " +  ctx.channel().remoteAddress());
        System.out.println("client receive : " +  msg);
        StringBuilder s = new StringBuilder("{\"growthAndGradeMsgModels\":[{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 10:44:25\",\"gradeName\":\"青铜会员\",\"growthVal\":115,\"growthValChangeTime\":\"2019-07-11 10:44:25\",\"memberNo\":\"E849000000040542296\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 14:01:58\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 14:01:58\",\"memberNo\":\"E311000000040543097\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 14:40:39\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 14:40:39\",\"memberNo\":\"E311000000040543099\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 15:35:40\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 15:35:40\",\"memberNo\":\"E311000000040540889\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 16:00:45\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 16:00:45\",\"memberNo\":\"E311000000040543101\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 19:21:10\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 19:21:10\",\"memberNo\":\"E311000000040542890\"}]}");

        ctx.channel().writeAndFlush("send to server : " + s.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
         ctx.channel().writeAndFlush("haha");
    }
}
