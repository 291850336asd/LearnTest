package com.meng.exapmle.serial.simple;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class JavaBaseSerialInt {

    public static void main(String[] args) throws Exception{
        int a = 11;
        int b = 22;
        int c = 33;

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(intToBytes(a));
        os.write(intToBytes(b));
        os.write(intToBytes(c));
        byte[] byteArray = os.toByteArray();
        System.out.println(Arrays.toString(byteArray));
        ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
        byte[] aBytes = new byte[4];
        byte[] bBytes = new byte[4];
        byte[] cBytes = new byte[4];
        is.read(aBytes);
        is.read(bBytes);
        is.read(cBytes);

        System.out.println("a: " + bytesToInt(aBytes));
        System.out.println("b: " + bytesToInt(bBytes));
        System.out.println("c: " + bytesToInt(cBytes));

    }

    /**
     * 将int值转换为四个字节的byte数组，低位在前，高位在后
     * @param value
     * @return
     */
    private static byte[] intToBytes(int value){
        byte[] byteArray = new byte[4];
        //最高位放在最后一个字节，也就是右移3个字节也就是24位
        byteArray[3] = (byte)((value & 0xFF000000) >> 24);//最高位，放在字节数据最后
        byteArray[2] = (byte)((value & 0x00FF0000) >> 16);
        byteArray[1] = (byte)((value & 0x0000FF00) >> 8);
        byteArray[0] = (byte)((value & 0x000000FF));
        return byteArray;
    }

    /**
     * byte数组 转int  低位在前，高位在后
     * @param byteArray
     * @return
     */
    private static int bytesToInt(byte[] byteArray){
        return  (byteArray[0] & 0xFF) |
                ((byteArray[1] << 8) & 0xFF00) |
                ((byteArray[2] << 16) & 0xFF0000) |
                ((byteArray[3] << 24) & 0xFF000000);

    }
}
