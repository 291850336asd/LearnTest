package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {

    public static void main(String[] args) {
//        BinaryOperator<Integer> binaryOperator = (v1, v2)->v1 + v2;
//        int total = binaryOperator.apply(1, 2);
//        System.out.println(total);

        System.out.println(compute(1,2, (v1, v2)-> v1 + v2));
        System.out.println(compute(1,2, (v1, v2)-> v1 - v2));

        System.out.println(BinaryOperator.maxBy((o1, o2) -> 0).apply(1,2));
        System.out.println(getShort("aa", "abc", (v1, v2) -> v1.length() - v2.length()));
        System.out.println(getShort("aa", "bbc", (v1, v2) -> v1.charAt(0) - v2.charAt(0)));
        System.out.println(getShort("aa", "ab", (v1, v2) -> -1));

    }



    public static int compute(int a, int b, BinaryOperator<Integer> binaryOperator){
        return binaryOperator.apply(a, b);
    }

    public static String getShort(String a, String b, Comparator<String> comparator){
        return BinaryOperator.minBy(comparator).apply(a, b);
    }
}
