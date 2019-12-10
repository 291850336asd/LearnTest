package com.meng.exapmle.concurrent.thread.simple;

public class ThreadSimple extends  Thread {

    private String name;

    public ThreadSimple(String name) {

        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name+"说大家好");
    }

    public static void main(String[] args) {
        new ThreadSimple("T1").start();
    }

}
