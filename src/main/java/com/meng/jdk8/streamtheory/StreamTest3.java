package com.meng.jdk8.streamtheory;


import java.util.Arrays;
import java.util.List;

public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        list.stream().forEach(System.out::println);
        System.out.println("--------------");
        list.stream().map(item -> item).filter(item -> true).forEach(System.out::println);
        System.out.println("--------------");
        list.parallelStream().forEach(System.out::println);
    }
}
