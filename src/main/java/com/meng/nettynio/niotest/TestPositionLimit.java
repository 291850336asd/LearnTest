package com.meng.nettynio.niotest;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class TestPositionLimit {

    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for(int i=0; i<5; i++){
            intBuffer.put(new SecureRandom().nextInt(20));
        }

        System.out.println("init data tht limmit : " + intBuffer.limit());
        System.out.println("init data tht position : " + intBuffer.position());


        intBuffer.flip();
        System.out.println("-----------after flip -----------");
        System.out.println("init data tht limmit : " + intBuffer.limit());
        System.out.println("init data tht position : " + intBuffer.position());
        System.out.println("-----------print all -----------");

        while (intBuffer.hasRemaining()){
            System.out.println(" data get  : " + intBuffer.get());
            System.out.println(" data tht limmit : " + intBuffer.limit());
            System.out.println(" data tht position : " + intBuffer.position());
            System.out.println(" data get  end");
        }

    }
}
