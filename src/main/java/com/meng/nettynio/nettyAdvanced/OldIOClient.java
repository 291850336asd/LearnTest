package com.meng.nettynio.nettyAdvanced;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class OldIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8899));
        String fileName = "D:\\android\\Android Studio\\lib\\idea.jar";
        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int readCount;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((readCount = inputStream.read(buffer)) >= 0){
            total += readCount;
            dataOutputStream.write(buffer, 0, readCount);
        }

        System.out.println("发送总字节数 ： " + total + "  , 耗时 ： " + (System.currentTimeMillis()- startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }

}
