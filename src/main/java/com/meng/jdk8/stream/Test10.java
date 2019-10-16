package com.meng.jdk8.stream;


import java.util.*;

public class Test10 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        List<String> list = Arrays.asList("hello", "world", "helloworld");

//        list.stream().mapToInt(s-> s.length()).filter(s-> s==5).findFirst().ifPresent(System.out::println);
        list.stream().mapToInt(s-> {
            System.out.println(s);
            return s.length();
        }).filter(s-> s==5).findFirst().ifPresent(System.out::println);
        //只输出hello   短路

        System.out.println("-----------");
        list.stream().mapToInt(s-> {
            System.out.println(s  + "   ---");
            return s.length();
        }).filter(s-> s==5).forEach(System.out::println);
    }

}
