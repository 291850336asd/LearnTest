package com.meng.jdk8.lambda.integerdefaultmethod;

public interface  MyInterface {
    default void methodDefault(){
        System.out.println("interface default method");
    }

    void method1();
}
