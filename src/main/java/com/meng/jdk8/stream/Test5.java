package com.meng.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test5 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        List<String> list = Arrays.asList("hello","world","helloworld");

        List newList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        newList.forEach(System.out::println);
        System.out.println("------------");

        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1,2), Arrays.asList(3,4), Arrays.asList(5,6, 1));
        //listStream.flatMap((listItem)->listItem.stream()).map(s-> s * s).collect(Collectors.toList()).forEach(System.out::println);
        listStream.flatMap((listItem)->listItem.stream()).map(s-> s * s).forEach(System.out::println);
        System.out.println("------------");
    }

}
