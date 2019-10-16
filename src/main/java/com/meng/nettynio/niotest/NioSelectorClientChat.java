package com.meng.nettynio.niotest;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioSelectorClientChat {

    public static void main(String[] args) throws Exception {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(8899));


            while (true){
                int readSelector = selector.select();
                if(readSelector <= 0){
                    continue;
                }
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeySet){
                    if(selectionKey.isConnectable()){
                        SocketChannel socketClient = (SocketChannel) selectionKey.channel();
                        if(socketClient.isConnectionPending()){
                            socketClient.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(512);
                            Charset charset = Charset.forName("gbk");
                            writeBuffer.put("连接成功".getBytes());
                            writeBuffer.flip();
                            socketClient.write(writeBuffer);
                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(() -> {
                               while (true){
                                   try {
                                       writeBuffer.clear();
                                       InputStreamReader input = new InputStreamReader(System.in);
                                       BufferedReader bufferedReader = new BufferedReader(input);
                                       String sendMessage = bufferedReader.readLine();
                                       writeBuffer.put(sendMessage.getBytes());
                                       writeBuffer.flip();
                                       socketClient.write(writeBuffer);

                                   }catch (Exception e){
                                       e.printStackTrace();
                                   }
                               }
                            });
                        }
                        socketClient.register(selector, SelectionKey.OP_READ);
                    } else if(selectionKey.isReadable()){
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        int readLength = client.read(byteBuffer);
                        if(readLength > 0){
                            byteBuffer.flip();
                            Charset charset = Charset.forName("gbk");
                            String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
                            System.out.println("client receivr : " + receiveMessage);
                        }

                    }
                }
                selectionKeySet.clear();
            }





        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
