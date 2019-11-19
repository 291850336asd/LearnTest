package com.meng.logging;

/**
 * volatile 内存可见性测试
 */
public class Thread1Demo extends Thread{
    private  boolean stop = false;
    @Override
    public void run() {
        int i = 0;
        while (!stop){
            i = 1;
        }
        System.out.println(i);
    }
    public void setStopTrue(){
        this.stop = true;
    }
    public static void main(String[] args) throws InterruptedException {
        Thread1Demo threadDemo = new Thread1Demo();
        threadDemo.start();
        Thread.sleep(1000);
        threadDemo.setStopTrue();
        System.out.println("over");
    }
}
