package com.meng.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test2 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        List<Integer> integerList = Arrays.asList(5, 6, 7);
        System.out.println(integerList.stream().mapToInt(s-> 2*s).sum());
        System.out.println(integerList.stream().mapToInt(s-> 2*s).reduce(0,Integer::sum));
    }

}
