package com.meng.nettynio.nettymillonwithdata;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyConnectCountHandler extends SimpleChannelInboundHandler<String> {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    static {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("current connections : " + atomicInteger.get());
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        byte[] bytes = new byte[1024* 20];
        StringBuilder s = new StringBuilder("{\"growthAndGradeMsgModels\":[{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 10:44:25\",\"gradeName\":\"青铜会员\"," +
                "\"growthVal\":115,\"growthValChangeTime\":\"2019-07-11 10:44:25\",\"memberNo\":\"E849000000040542296\"}," +
                "{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 14:01:58\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 14:01:58\"," +
                "\"memberNo\":\"E311000000040543097\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 14:40:39\",\"gradeName\":\"青铜会员\"," +
                "\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 14:40:39\",\"memberNo\":\"E311000000040543099\"},{\"grade\":1,\"gradeChangeTime\":" +
                "\"2019-07-11 15:35:40\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 15:35:40\",\"memberNo\":\"E311000000040540889\"}," +
                "{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 16:00:45\",\"gradeName\":\"青铜会员\",\"growthVal\":5,\"growthValChangeTime\":\"2019-07-11 16:00:45\"," +
                "\"memberNo\":\"E311000000040543101\"},{\"grade\":1,\"gradeChangeTime\":\"2019-07-11 19:21:10\",\"gradeName\":\"青铜会员\",\"growthVal\":5," +
                "\"growthValChangeTime\":\"2019-07-11 19:21:10\",\"memberNo\":\"E311000000040542890\"}]}");
        s.append(new Date().getTime());
        Thread.sleep(100);
        ctx.channel().writeAndFlush("server : " + s.toString());
        bytes = null;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.decrementAndGet();
    }

}
