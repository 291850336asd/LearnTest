package com.meng.jdk8.stream;


import java.util.Arrays;
import java.util.List;

public class Test12 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        List<String> list1 = Arrays.asList("zhangsan", "lisi", "wamgwu");
        List<String> list2 = Arrays.asList("Hi", "Hello", "你好");

        list2.stream().forEach(item-> list1.stream().forEach(item2 -> System.out.println(item + " " + item2)));

        System.out.println("-----------------");
        list2.stream().flatMap(item -> list1.stream().map(item2 -> item + item2)).forEach(System.out::println);
    }

}
