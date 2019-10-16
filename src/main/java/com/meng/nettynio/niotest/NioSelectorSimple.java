package com.meng.nettynio.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSelectorSimple {

    public static void main(String[] args) throws IOException {

        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;

        Selector selector = Selector.open();

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(ports[i]));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("端口监听：" + ports[i]);
        }


        while (true){
            int readyChannels  = selector.select();
            System.out.println("select num : " + readyChannels);

            if(readyChannels == 0){
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()) {
                    System.out.println(" a connection was accepted by a ServerSocketChannel.");
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("获取客户端连接 : " + socketChannel);
                } else if (key.isConnectable()) {
                    System.out.println("a connection was established with a remote server.");
                } else if (key.isReadable()) {
                    System.out.println(" a channel is ready for reading");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int readLength = socketChannel.read(byteBuffer);
                        if(readLength <= 0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                    }


                } else if (key.isWritable()) {
                    System.out.println("a channel is ready for writing");
                }

                //Notice the keyIterator.remove() call at the end of each iteration.
                // The Selector does not remove the SelectionKey instances from the selected key set itself.
                // You have to do this, when you are done processing the channel.
                // The next time the channel becomes "ready" the Selector will add it to the selected key set again.
                keyIterator.remove();
            }
        }
    }

}
