package com.meng.nettynio.niobytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class ByteBufTest2 {

    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf direcBuf = Unpooled.directBuffer(8);

        compositeByteBuf.addComponent(heapBuf);
        compositeByteBuf.addComponent(direcBuf);

       // compositeByteBuf.removeComponent(0);
        Iterator<ByteBuf> iterator = compositeByteBuf.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }



    }
}
