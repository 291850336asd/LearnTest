package com.meng.jdk8.streamtheory;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
//        try(Stream<String> lisStream = list.stream()){
//            lisStream.onClose(()->{
//                System.out.println("aaa");
//                throw new NullPointerException("first close error");
//            }).onClose(() ->{
//                System.out.println("bbb");
//                throw new NullPointerException("second close error");
//            }).forEach(System.out::println);
//        }

        System.out.println("---------------");
        NullPointerException nullPointerException = new NullPointerException("my exception");
        try(Stream<String> lisStream = list.stream()){
            lisStream.onClose(()->{
                System.out.println("aaa");
                throw nullPointerException;
            }).onClose(() ->{
                System.out.println("bbb");
                throw nullPointerException;
            }).forEach(System.out::println);
        }
    }
}
