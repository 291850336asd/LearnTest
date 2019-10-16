package com.meng.nettynio.niotest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class TestFirstReadRandomFromMapperFile {


    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("mapper.txt", "rw");
        FileChannel inChannel = aFile.getChannel();


        FileLock fileLock = inChannel.lock(0,5, true);
        System.out.println("local infns  " +  fileLock.isValid());
        MappedByteBuffer mappedByteBuffer =  inChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(3, (byte) 'b');
        System.out.println(mappedByteBuffer.get(1) - '0');
        fileLock.release();
        aFile.close();
    }

}
