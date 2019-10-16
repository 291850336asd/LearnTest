package com.meng.nettynio.nettyAdvanced;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(true);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress("localhost",8899));


        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);
            int readCount = 0;
            long count = 0;
            while (-1 != readCount){
                try {
                    byteBuffer.clear();
                    readCount = socketChannel.read(byteBuffer);
                    count += readCount;
                }catch (Exception e){
                    System.out.println(count);
                    e.printStackTrace();
                }


            }
        }
    }
}
