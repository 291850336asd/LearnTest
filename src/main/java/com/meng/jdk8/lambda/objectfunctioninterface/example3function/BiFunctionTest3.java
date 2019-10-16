package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionTest3 {

    public static void main(String[] args) {
        BiFunctionTest3 test = new BiFunctionTest3();
        System.out.println(test.computeFunction(3, value->value*2, value->value*value));
        System.out.println(test.computeFunction2(3, value->value*2, value->value*value));
        System.out.println("----------------");
        System.out.println(test.computeBiFunction1(1, 2, (v1, v2)-> v1 + v2));
        System.out.println(test.computeBiFunction1(1, 2, (v1, v2)-> v1 - v2));
        System.out.println(test.computeBiFunction1(1, 2, (v1, v2)-> v1 * v2));
        System.out.println(test.computeBiFunction1(1, 2, (v1, v2)-> v1 / v2));
        System.out.println("----------------");
        System.out.println(test.computeBiFunction2(1, 2, (v1, v2)-> v1 + v2, value-> 2 * value));
    }

    public int computeFunction(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        return function1.compose(function2).apply(a);
    }

    public int computeFunction2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        return function1.andThen(function2).apply(a);
    }

    public int computeBiFunction1(int a, int b, BiFunction<Integer, Integer, Integer> biFunction){
        return biFunction.apply(a, b);
    }


    public int computeBiFunction2(int a, int b, BiFunction<Integer, Integer, Integer> biFunction,
                                  Function<Integer, Integer> function){
        return biFunction.andThen(function).apply(a, b);
    }

}
