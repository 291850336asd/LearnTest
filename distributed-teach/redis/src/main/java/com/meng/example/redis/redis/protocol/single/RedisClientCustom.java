package com.meng.example.redis.redis.protocol.single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RedisClientCustom {



    private String host;
    private Integer port;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;


    public static void main(String[] args) {
        RedisClientCustom redisClientCustom = new RedisClientCustom("192.168.163.128",6379);
        redisClientCustom.sendCommands(ProtocolRedis.Command.SET, "meng".getBytes(), "newInfos".getBytes());
        String response = redisClientCustom.replyMsgCode();
        System.out.println(response);
    }


    public RedisClientCustom(String host, Integer port) {
        this.host = host;
        this.port = port;
        this.connect();
    }

    public void connect(){
        try {
            if(!(socket != null && socket.isBound() && !socket.isClosed())){
                socket = new Socket(this.host, this.port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void sendCommands(ProtocolRedis.Command command, byte[]... bytes){
        ProtocolRedis.command(outputStream, command, bytes);
    }

    public String replyMsgCode() {
        byte[] bytes = new byte[1024];
        try {
            this.inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }


    /**
     * 符号解释：
     * For Simple Strings the first byte of the reply is "+" 回复
     * For Errors the first byte of the reply is "-" 错误
     * For Integers the first byte of the reply is ":" 整数
     * For Bulk Strings the first byte of the reply is "$" 字符串
     * For Arrays the first byte of the reply is "*" 数组
     */
    private static class ProtocolRedis{
        private static final String DOLLOR = "$";
        private static final String ASTERISK = "*";
        private static final String CRLF = "\r\n" ;

        public static void command(OutputStream out, Command command, byte[]... bytes){
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(ASTERISK).append(bytes.length + 1).append(CRLF);
            stringBuffer.append(DOLLOR).append(command.name().length()).append(CRLF);
            stringBuffer.append(command.name()).append(CRLF);
            for (byte[] arg: bytes) {
                stringBuffer.append(DOLLOR).append(arg.length).append(CRLF);
                stringBuffer.append(new String(arg)).append(CRLF);
            }
            stringBuffer.append(CRLF);
            System.out.println(stringBuffer.toString());
            try {
                out.write(stringBuffer.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         * 枚举出所有redis操作
         */
        public static enum Command {
            GET, SET, KEYS
        }

    }

}
