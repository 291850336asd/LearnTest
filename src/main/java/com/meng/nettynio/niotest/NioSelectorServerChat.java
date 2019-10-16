package com.meng.nettynio.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioSelectorServerChat {

    private static Map<String, SocketChannel> map = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                int readyChannels  = selector.select();
                if(readyChannels == 0){
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                selectedKeys.forEach(selectionKey -> {
                    try {
                        if(selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel socketChannel = server.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            String key = UUID.randomUUID().toString();
                            map.put(key, socketChannel);
                            System.out.println("获取客户端连接 : " + socketChannel);
                        } else if (selectionKey.isConnectable()) {

                        } else if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                            int readLength = socketChannel.read(byteBuffer);
                            if(readLength > 0){
                                byteBuffer.flip();
                                Charset charset = Charset.forName("gbk");
                                String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
                                System.out.println(receiveMessage);

                                map.forEach((key,value) -> {
                                    if(value != socketChannel){
                                        ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                                        writeBuffer.put(("write : " + receiveMessage).getBytes());
                                        writeBuffer.flip();
                                        try {
                                            value.write(writeBuffer);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                socketChannel.write(byteBuffer);
                            }
                        } else if (selectionKey.isWritable()) {
                            System.out.println("a channel is ready for writing");
                        }

                    }catch (Exception e){}
                });
                selectedKeys.clear();
            }catch (Exception e){

            }
        }
    }

}
