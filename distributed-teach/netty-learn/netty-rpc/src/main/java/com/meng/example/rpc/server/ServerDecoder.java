package com.meng.example.rpc.server;

import com.meng.example.rpc.codec.Codec;
import com.meng.example.rpc.codec.CodecFactory;
import com.meng.example.rpc.protocol.ProtocolRequestEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServerDecoder extends ByteToMessageDecoder {

    //{[body.length]}body

    private Codec codec = CodecFactory.codec();

    private int bodyLen = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) throws Exception {
        //先解析包头
        if (bodyLen == 0 && buf.readableBytes() >= 8) {
            //{[  ]}  标记位处理，这里不做演示
            byte[] L = new byte[2];
            byte[] len = new byte[4];
            byte[] R = new byte[2];
            buf.readBytes(L);
            buf.readBytes(len);
            buf.readBytes(R);
            String Ls = new String(L, StandardCharsets.UTF_8);
            String Rs = new String(R, StandardCharsets.UTF_8);
            //获取包体长度
            bodyLen = (len[0] & 0xFF) << 24 | (len[1] & 0xFF) << 16 | (len[2] & 0xFF) << 8 | len[3] & 0xFF;
            System.out.println("包头：" + Ls + bodyLen + Rs);
        }
        //然后解析包体
        if (bodyLen > 0 && buf.readableBytes() >= bodyLen) {
            byte[] body = new byte[bodyLen];
            buf.readBytes(body);
            bodyLen = 0;
            String result = new String(body, StandardCharsets.UTF_8);
            System.out.println("包体：" + result);
            //反序列化对于内部的params属性做特殊处理
            Object entity = codec.decode(body, ProtocolRequestEntity.class);
            list.add(entity);
        }
    }

}
