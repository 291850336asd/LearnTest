package com.meng.jdk8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test6 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        Stream<String> stringStream = Stream.generate(()->UUID.randomUUID().toString());
        stringStream.findFirst().ifPresent(System.out::println);

        //无限流
        IntStream stream = Stream.iterate(1, item-> item+ 2).limit(6).filter(s-> s>2).mapToInt(s-> 2*s).skip(2).limit(2);
        stream.forEach(System.out::println);

        int sum =  Stream.iterate(1, item-> item+ 2).limit(2).filter(s-> s>2).mapToInt(s-> 2*s).skip(2).limit(2).sum();
        System.out.println(sum);

        int min =  Stream.iterate(1, item-> item+ 2).limit(2).filter(s-> s>2).mapToInt(s-> 2*s).skip(2).limit(2).min().orElseGet(()->-1);
        System.out.println(min);
        System.out.println("--------------");
        IntSummaryStatistics intSummaryStatistics = Stream.iterate(1, item-> item+ 2).limit(6).mapToInt(Integer::intValue).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getAverage());


    }

}
