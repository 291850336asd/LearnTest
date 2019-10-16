package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import java.util.function.Function;

public class FunctionTest1 {

    public static void main(String[] args) {
        System.out.println(compute(1, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        }));
        System.out.println(compute(1, integer -> integer*2));
        System.out.println(compute(1, integer -> integer + 5));
        System.out.println(compute(1,  integer -> {return integer + 5;}));

        System.out.println(computeString(1,  integer -> integer + "5"));
        System.out.println(computeString(1,  integer -> integer + "djdhfdj"));
    }

    public static int compute(int i, Function<Integer, Integer> function){
        return function.apply(i);
    }

    public static String computeString(int i, Function<Integer, String> function){
        return function.apply(i);
    }
}
