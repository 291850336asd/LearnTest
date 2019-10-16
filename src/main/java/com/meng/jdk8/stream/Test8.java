package com.meng.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test8 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        Stream.iterate(1, item-> item+ 2).distinct().limit(6).forEach(System.out::println);

        Stream.iterate(1, item-> item+ 2).distinct().limit(6).forEach(System.out::println);
    }

}
