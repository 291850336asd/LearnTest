package com.meng.jdk8.lambda.objectfunctioninterface.example2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MyTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");
        list.stream().map(item -> item.toUpperCase()).forEach(item-> System.out.println(item));
        list.stream().map(String::toUpperCase).forEach(item-> System.out.println(item));

        Function<String, String> function = String::toUpperCase;
       // Function<String, String> function = s -> s.toUpperCase();
        System.out.println(function.getClass().getInterfaces()[0]);
    }

}
