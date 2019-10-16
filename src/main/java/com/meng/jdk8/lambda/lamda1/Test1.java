package com.meng.jdk8.lambda.lamda1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test1 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("========================");
        for (Integer item: list) {
            System.out.println(item);
        }
        System.out.println("========================");
//        Note that instances of functional interfaces can be created with
//        lambda expressions, method references, or constructor references.
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("========================");
        //lambda expressions,
        list.forEach(integer -> System.out.println(integer));
        System.out.println("========================");
        //method references
        list.forEach(System.out::println);
        System.out.println("========================");
    }
}
