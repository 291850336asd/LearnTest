package com.meng.nettynio.niotest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class DireBuffer {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("input2.txt");
        FileOutputStream outputStream = new FileOutputStream("output2.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3);

        while (true){
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            System.out.println(read);
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inputChannel.close();
        outputChannel.close();

    }
}
