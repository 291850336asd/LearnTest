package com.meng.nettynio.niobytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBufTest1 {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world 我", Charset.forName("utf-8"));
        boolean isArray = byteBuf.hasArray();
        if(isArray){
            byte[] infos = byteBuf.array();
            System.out.println(new String(infos, Charset.forName("utf-8")));
            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.readableBytes());
            System.out.println(byteBuf.capacity());
            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                System.out.print((char)byteBuf.getByte(i));
            }
            System.out.println();
            System.out.println(new String(new byte[]{(byte)'￦',(byte)'ﾈ',(byte)'ﾑ'}));
        }
    }
}
