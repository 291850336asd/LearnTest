package com.meng.jdk8.lambda.objectfunctioninterface.example3function;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class PredicateTest1 {

    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 5;
        System.out.println(predicate.test("hello"));

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("---------------");
        conditionFilter(list, s-> s%2 == 0);
        System.out.println("---------------");
        conditionFilterAnd(list, s-> s%2 == 0, s-> s > 5);
        System.out.println("---------------");
        conditionFilterOr(list, s-> s%2 == 0, s-> s > 5);
        System.out.println("---------------");
        conditionFilterOrNegate(list, s-> s%2 == 0, s-> s > 5);
        System.out.println(Objects.equals(1,1));

        System.out.println(Predicate.isEqual(1).test(1));
        System.out.println(Predicate.isEqual(1).test(2));
        System.out.println(isEqual("dsds").test("dsds"));
    }

    public static void conditionFilter(List<Integer> list, Predicate<Integer> predicate){
        for (Integer integer: list){
            if(predicate.test(integer)){
                System.out.println(integer);
            }
        }
    }

    public static void conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate,
                                       Predicate<Integer> predicate2){
        for (Integer integer: list){
            if(predicate.and(predicate2).test(integer)){
                System.out.println(integer);
            }
        }
    }

    public static void conditionFilterOr(List<Integer> list, Predicate<Integer> predicate,
                                          Predicate<Integer> predicate2){
        for (Integer integer: list){
            if(predicate.or(predicate2).test(integer)){
                System.out.println(integer);
            }
        }
    }

    public static void conditionFilterOrNegate(List<Integer> list, Predicate<Integer> predicate,
                                         Predicate<Integer> predicate2){
        for (Integer integer: list){
            if(predicate.or(predicate2).negate().test(integer)){
                System.out.println(integer);
            }
        }
    }

    public static Predicate<String> isEqual(Object object){
        return Predicate.isEqual(object);
    }
}
