package com.meng.jdk8.lambda.objectfunctioninterface.example2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MyTest2 {

    public static void main(String[] args) {
//        test1();
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.forEach(item->{
            System.out.println(item.toUpperCase());
        });

        List<String> newList = new ArrayList<>();//diamond 语法  类型推断
        list.forEach(item->newList.add(item.toUpperCase()));
        newList.forEach(item-> System.out.println(item));

        System.out.println("------ -> ------");
        list.stream().map(item -> item.toUpperCase()).forEach(item-> System.out.println(item));

        System.out.println("------::------");
        list.stream().map(String::toUpperCase).forEach(item-> System.out.println(item));
        System.out.println("-------------");
        List<String> stringList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        stringList.forEach(System.out::println);

    }

    private static void test1() {
        TheInterface theInterface = ()->{};
        System.out.println(theInterface.getClass().getInterfaces()[0]);
        TheInterface2 theInterface2 = ()->{};
        System.out.println(theInterface2.getClass().getInterfaces()[0]);

        new Thread(()->{ System.out.println("run.."); }).start();
        new Thread(()-> System.out.println("run..")).start();
    }

}

@FunctionalInterface
interface TheInterface {
    void myMethod();
}

@FunctionalInterface
interface TheInterface2 {
    void myMethod2();
}