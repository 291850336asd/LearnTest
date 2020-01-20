package com.meng.example.rpc.client;


import com.meng.example.rpc.protocol.ProtocolResponseEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<ProtocolResponseEntity> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolResponseEntity entity) throws Exception {
        ClientChannel cc = (ClientChannel) ctx.channel();
        cc.set(entity);
    }

}
