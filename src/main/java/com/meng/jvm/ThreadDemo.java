package com.meng.jvm;

/**
 * volatile 内存可见性测试
 */
public class ThreadDemo extends Thread{
    //若不设置 volatile 则无法结束main方法
    // 因为  threadDemo.start(); 与  threadDemo.setStopTrue();不在同一线程 stop不共享
    private volatile boolean stop = false;
    @Override
    public void run() {
        int i = 0;
        while (!stop){
            i ++;
        }
        System.out.println(i);
    }
    public void setStopTrue(){
        this.stop = true;
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
        Thread.sleep(1000);
        //若不设置 volatile 则无法结束main方法
        // 因为  threadDemo.start(); 与  threadDemo.setStopTrue();不在同一线程 stop不共享
        threadDemo.setStopTrue();
        System.out.println("over");
    }
}
