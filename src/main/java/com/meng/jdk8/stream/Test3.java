package com.meng.jdk8.stream;

import java.util.stream.IntStream;

public class Test3 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        IntStream intStream = IntStream.of(new int[]{5, 6, 7});
        intStream.forEach(System.out::println);
        System.out.println("----------------");
        IntStream.range(3,8).forEach(System.out::println);
        System.out.println("----------------");
        IntStream.rangeClosed(3,8).forEach(System.out::println);
        System.out.println("----------------");
    }

}
