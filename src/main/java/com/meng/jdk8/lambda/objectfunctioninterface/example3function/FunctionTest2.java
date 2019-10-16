package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import java.util.function.Function;

public class FunctionTest2 {

    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();
        System.out.println(test.computeFunction(3, value->value*2, value->value*value));
        System.out.println(test.computeFunction2(3, value->value*2, value->value*value));

    }

    public int computeFunction(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        return function1.compose(function2).apply(a);
    }

    public int computeFunction2(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2){
        return function1.andThen(function2).apply(a);
    }
}
