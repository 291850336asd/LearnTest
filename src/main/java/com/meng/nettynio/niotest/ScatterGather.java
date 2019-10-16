package com.meng.nettynio.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatterGather {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(8899));

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int bytesRead = 0;

            while (bytesRead < messageLength){
                long r = socketChannel.read(byteBuffers);
                bytesRead += r;
                System.out.println("bytesRead : " + bytesRead);
                Arrays.asList(byteBuffers).forEach(item -> {
                    System.out.println("buffer position : " + item.position() + " limit : " + item.limit());
                });

            }
            Arrays.asList(byteBuffers).forEach(item -> {
                item.flip();
            });

            long bytesWritten = 0;
            while (bytesWritten < messageLength){
                long r = socketChannel.write(byteBuffers);
                bytesWritten += r;
            }
            Arrays.asList(byteBuffers).forEach(s -> {
                s.clear();
            });
            System.out.println("bytesRead : " + bytesRead
                    + " , bytesWritten : " + bytesWritten
                    + " , messageLength : " + messageLength );
        }
    }

}
