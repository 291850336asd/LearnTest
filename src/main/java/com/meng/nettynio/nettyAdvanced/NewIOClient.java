package com.meng.nettynio.nettyAdvanced;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);


        String fileName =  "D:\\android\\Android Studio\\lib\\idea.jar";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long position = 0;
        long size = fileChannel.size();
        long total = 0;
        long startTime = System.currentTimeMillis();
        while (position < size) {

            // windows对一次传输的大小是有限制的； Windows has a hard limit on the maximum transfer size, and if you exceed it you get a runtime exception.
            // So you need to tune. The second version you give is superior because it doesn't assume the file was transferred completely with one transferTo() call, which agrees with the Javadoc.
            // Setting the transfer size more than about 1MB is pretty pointless anyway.
            long transfer = fileChannel.transferTo(position, fileChannel.size(), socketChannel);
            System.out.println("发送：" + transfer);
            if (transfer <= 0) {
                break;
            }
            total += transfer;
            position += transfer;
        }
        System.out.println("发送总字节数 ： " + total + "  , 耗时 ： " + (System.currentTimeMillis()- startTime));
        fileChannel.close();
    }
}
