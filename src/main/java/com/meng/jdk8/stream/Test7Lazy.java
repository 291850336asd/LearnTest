package com.meng.jdk8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test7Lazy {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值
        List<String> list = Arrays.asList("hello", "world");

        //do nothinng
        list.stream().map(item->{
           String result = item.substring(0,1).toUpperCase() + item.substring(1);
            System.out.println(result);
           return result;
        });

        
    }

}
