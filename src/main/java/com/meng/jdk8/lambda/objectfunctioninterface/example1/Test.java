package com.meng.jdk8.lambda.objectfunctioninterface.example1;


public class Test{

    public  static void test(MyInterface myInterface){
        System.out.println(1);
        myInterface.test();
        System.out.println(2);
    }

    public static void main(String[] args) {
        test(new MyInterface() {
            @Override
            public void test() {
                System.out.println("mytest");
            }
        });
        test(()->{
            System.out.println("mytest");
        });

        MyInterface myInterface = () ->{
            System.out.println("mytest");
        };
        System.out.println(myInterface);
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);
    }
}
