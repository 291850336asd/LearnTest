package com.meng.nettynio.nettymillon;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyConnectCountHandler extends ChannelInboundHandlerAdapter {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    static {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("current connections : " + atomicInteger.get());
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    public MyConnectCountHandler(){

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
