package com.meng.jdk8.streamtheory;

public class LambdaTest {

    Runnable r1 = () -> System.out.println("r1 : " + this);

    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            System.out.println("r2 : " + this);
        }
    };

    public static void main(String[] args) {
        LambdaTest test = new LambdaTest();
        Thread thread1 = new Thread(test.r1);
        thread1.start();
        Thread thread2 = new Thread(test.r2);
        thread2.start();
        System.out.println("main : " + test);
    }

}
