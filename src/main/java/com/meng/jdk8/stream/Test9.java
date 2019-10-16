package com.meng.jdk8.stream;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Test9 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        long begin = System.nanoTime();
        List<String> list = new ArrayList<>();
        for (int i=0; i<5000000; i++){
            list.add(UUID.randomUUID().toString());
        }
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(end - begin));

        list.parallelStream().sorted().count();
//        list.stream().sorted().count();
        long sortEnd = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(sortEnd - end));
    }

}
