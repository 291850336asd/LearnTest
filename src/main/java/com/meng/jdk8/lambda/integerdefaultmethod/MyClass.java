package com.meng.jdk8.lambda.integerdefaultmethod;

public class MyClass implements MyInterface, MyInterface2 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.methodDefault();
    }

    @Override
    public void methodDefault() {
        System.out.println("MyClass methodDefault");
    }

    @Override
    public void method1() {

    }

}
