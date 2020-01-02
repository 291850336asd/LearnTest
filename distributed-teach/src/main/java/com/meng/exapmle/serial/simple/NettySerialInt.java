package com.meng.exapmle.serial.simple;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.Arrays;

public class NettySerialInt {

    public static void main(String[] args) {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(11);
        buffer.writeInt(22);
        buffer.writeInt(33);
        //byte数组的大小由buffer中写指针的位置决定
        //往channelbuffer中写数据的时候，这个写指针就会移动写的数据长度
        byte[] bytes = new byte[buffer.writerIndex()];
        buffer.readBytes(bytes);
        System.out.println(Arrays.toString(bytes));

        //反序列化
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(bytes);
        System.out.println(wrappedBuffer.readerIndex());
        System.out.println(wrappedBuffer.readerIndex());

    }

}
