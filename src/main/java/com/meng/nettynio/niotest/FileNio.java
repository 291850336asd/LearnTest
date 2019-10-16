package com.meng.nettynio.niotest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNio {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("input.txt");
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileChannel outputChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(3);


        while (true){
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            System.out.println(read);
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
            System.out.println("position : " + byteBuffer.position());
            Thread.sleep(1000);
        }

        inputChannel.close();
        outputChannel.close();

    }

}
