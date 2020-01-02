package com.meng.exapmle.serial.simple;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class NioSerialInt {

    public static void main(String[] args) {
        int a = 11;
        int b = 22;
        //序列化
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putInt(a);
        buffer.putInt(b);

        byte[] array = buffer.array();
        System.out.println(Arrays.toString(array));

        //反序列化
        ByteBuffer bb = ByteBuffer.wrap(array);
        System.out.println("a: " + bb.getInt());
        System.out.println("b: " + bb.getInt());
    }

}
