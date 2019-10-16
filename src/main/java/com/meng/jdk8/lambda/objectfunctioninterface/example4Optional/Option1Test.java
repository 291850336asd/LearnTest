package com.meng.jdk8.lambda.objectfunctioninterface.example4Optional;


import java.util.Optional;

public class Option1Test {

    public static void main(String[] args) {
        Optional<String> optionalS = Optional.of("hello");
        if(optionalS.isPresent()){
            System.out.println(optionalS.get());
        }
        optionalS.ifPresent(System.out::println);
        optionalS.ifPresent(s -> System.out.println(s));

        Optional<String> optionalS2 = Optional.empty();
        System.out.println(optionalS2.orElse("world"));
        System.out.println(optionalS2.orElseGet(() -> "world"));
        try {
            System.out.println(optionalS2.orElseThrow(()->new RuntimeException("error info")));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
