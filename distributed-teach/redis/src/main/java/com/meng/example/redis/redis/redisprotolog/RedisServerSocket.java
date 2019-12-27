package com.meng.example.redis.redis.redisprotolog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RedisServerSocket {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6380);
            while (true){
                Socket socket = serverSocket.accept();
                if(socket != null && socket.isBound() && !socket.isClosed()){
                    new Thread(new SocketListener(socket)).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class SocketListener implements Runnable {

        Socket socket;


        public SocketListener(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

            byte[] bytes = new byte[1024];
            try {
                socket.getInputStream().read(bytes);


            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("accept data : -> " + new String(bytes));
//            accept data : -> *3
//            $3
//            SET
//            $4
//            meng
//            $4
//            info

        }
    }


}
