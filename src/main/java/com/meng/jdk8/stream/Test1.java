package com.meng.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test1 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值


        Stream stream1 = Stream.of("hello","world","helloworld");
        String[] myArray =new String[]{"hello","world","helloworld"};
        Stream stream2 = Stream.of(myArray);
        Stream stream3 = Arrays.stream(myArray);

        List<String> list = Arrays.asList(myArray);
        Stream stream4 = list.stream();
    }

}
