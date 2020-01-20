package com.meng.example.rpc.server;

import com.meng.example.rpc.codec.Codec;
import com.meng.example.rpc.codec.CodecFactory;
import com.meng.example.rpc.protocol.ProtocolRequestEntity;
import com.meng.example.rpc.protocol.ProtocolResponseEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

public class ServerHandler extends SimpleChannelInboundHandler<ProtocolRequestEntity> {

    private Codec codec = CodecFactory.codec();

    private Map<String, ServiceContainer> services;

    public ServerHandler(Map<String, ServiceContainer> services) {
        this.services = services;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolRequestEntity entity) throws Exception {
        String intfName = entity.getClazzName().replaceAll("interface ", "");
        String methodName = entity.getMethodName();
        byte[] params_bytes = entity.getParams();

        //把参数二进制数组转换成执行函数的入参，这里需要做参数转换
        Object[] params = codec.decode(params_bytes, Object[].class);

        ServiceContainer service = this.services.get(intfName);
        //需要考虑执行异常情况，把执行异常包装给客户端
        Object result = service.invoke(methodName, params);

        //包装执行结果
        ProtocolResponseEntity resultEntity = new ProtocolResponseEntity();
        //考虑 result = null 的情况，这里不做演示
        resultEntity.setError("no error".getBytes());
        resultEntity.setResult(codec.encode(result));
        ctx.channel().writeAndFlush(resultEntity);
    }

}
