package com.meng.exapmle.serial.protobuf.javaraw;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class JavaSerial {

    public static void main(String[] args) throws Exception{
        //序列化
        long startTime = System.currentTimeMillis();
        for(int i= 0;i<=100000; i++){
            byte[] result = serializeObject();
            if(i == 0){
                System.out.println(Arrays.toString(result));
            }

            //反序列化
            SubscribeReq req = deserializeObject(result);
            if(i ==0){
                System.out.println(req.getSubReqID());
                System.out.println(req.getUserName());
                System.out.println(req.getProductName());
                System.out.println(req.getAddressList().get(0));
                System.out.println(req.getAddressList().get(1));
                System.out.println(req.getAddressList().get(2));
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private static byte[] serializeObject() throws Exception{
        SubscribeReq req = new SubscribeReq();
        req.setSubReqID(1);
        req.setUserName("abc");
        req.setProductName("netty");
        req.setAddressList(Arrays.asList("长沙", "深圳", "北京"));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(req);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    private static SubscribeReq deserializeObject(byte[] byteArray) throws Exception{
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArray));
        return (SubscribeReq) objectInputStream.readObject();
    }

}
