package com.meng.jdk8.streamtheory;

import java.util.*;

public class Test2Comparator {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhangsan", "lisi", "aaaa","wangwu", "zhaoliu");
//        Collections.sort(list, Comparator.comparingInt(String::length));
//        System.out.println(list);
//        Collections.sort(list, Comparator.comparingInt(String::length).reversed());
//        System.out.println(list);
//        Collections.sort(list, (item1, itenm2)-> item1.length() - itenm2.length());
        Collections.sort(list, Comparator.comparingInt(s-> s.length()));
        Collections.sort(list, Comparator.comparingInt((String s)-> s.length()).reversed());
        System.out.println(list);
        Collections.sort(list, Comparator.comparingInt((String s)-> s.length()).thenComparing(String.CASE_INSENSITIVE_ORDER));
        System.out.println(list);
        Collections.sort(list, Comparator.comparingInt((String s)-> s.length())
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        System.out.println(list);
        Collections.sort(list, Comparator.comparingInt((String s)-> s.length()).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())));
        System.out.println(list);
        Collections.sort(list, Comparator.comparingInt((String s)-> s.length()).reversed()
                .thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder()))
                        .thenComparing(Comparator.reverseOrder()));
        System.out.println(list);
    }
}
