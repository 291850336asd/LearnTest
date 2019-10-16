package com.meng.jdk8.lambda.objectfunctioninterface.example1;

@FunctionalInterface
public interface MyInterface {
    void test();

//    void test2(); ///error
    String toString();

    int hashCode();
}


