package com.meng.exapmle.concurrent.thread.simple;

import java.util.concurrent.TimeUnit;

public class ThreadRunnable implements    Runnable{

    private String name;

    public ThreadRunnable(String name) {

        this.name = name;
    }

    @Override
    public void run() {

        System.out.println("1");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {


        }
        for (int i=0;i<1000000;i++){

            System.out.println(i);
        }

        System.out.println(name+"说大家好");
    }

    public static void main(String[] args) {
        //new WukongThread2("T1").start();

        new Thread(new ThreadRunnable("T2")).start();
    }

}
