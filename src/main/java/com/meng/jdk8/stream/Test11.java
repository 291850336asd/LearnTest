package com.meng.jdk8.stream;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test11 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        List<String> list = Arrays.asList("hello world", "world hello", "hello welcome", "hello world hello","welcome world");

        list.stream().flatMap(s-> Arrays.asList(s.split(" ")).stream()).distinct().forEach(System.out::println);
    }

}
