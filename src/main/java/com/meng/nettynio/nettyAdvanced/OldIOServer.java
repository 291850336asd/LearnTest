package com.meng.nettynio.nettyAdvanced;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIOServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8899);
        while(true){
            System.out.println("wait read");
            Socket socket = serverSocket.accept();
            System.out.println("begin read");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] byteArray = new byte[4096];
                while (true){
                    int readLength = dataInputStream.read(byteArray, 0 , byteArray.length);
                    if(readLength == -1){
                        break;
                    }
                }
            }catch (Exception e){}


        }


    }
}
