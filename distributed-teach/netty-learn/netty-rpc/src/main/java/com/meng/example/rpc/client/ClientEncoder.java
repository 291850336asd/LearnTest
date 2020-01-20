package com.meng.example.rpc.client;

import com.meng.example.rpc.codec.Codec;
import com.meng.example.rpc.codec.CodecFactory;
import com.meng.example.rpc.protocol.Const;
import com.meng.example.rpc.protocol.ProtocolRequestEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ClientEncoder extends MessageToByteEncoder<ProtocolRequestEntity> {

    //{[body.length]}body

    private Codec codec = CodecFactory.codec();

    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolRequestEntity entity, ByteBuf buf) throws Exception {
        byte[] body = codec.encode(entity);
        int bodyLen = body.length;
        byte[] len = new byte[4];
        len[0] = (byte)((bodyLen >> 24) & 0xFF);
        len[1] = (byte)((bodyLen >> 16) & 0xFF);
        len[2] = (byte)((bodyLen >> 8) & 0xFF);
        len[3] = (byte)(bodyLen & 0xFF);
        //包头
        buf.writeBytes(Const.L);
        buf.writeBytes(len);
        buf.writeBytes(Const.R);
        //包体
        buf.writeBytes(body);
    }

}
