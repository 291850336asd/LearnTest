package com.meng.jvm;

/**
 * 指令重排序
 */
public class SyncchnorizedDemo {
    //共享变量
    private boolean flag = false;
    private int a= 1;
    private int result = 0;

    //写操作
    public synchronized void write(){
        //若不用synchronized   可能会发生指令重排,导致最终的结果错误
        flag = true;
        a=2;
    }

    //读操作
    public synchronized void read(){
        if(flag) {
            result = a *3;
        }
        System.out.println("result: " + result);
    }

    private class ReadWriteThread extends Thread {

        private boolean flag;
        public ReadWriteThread(boolean flag){
            this.flag = flag;
        }

        @Override
        public void run() {
            if(flag) {
                write();
            } else {
                read();
            }
        }
    }

    public static void main(String[] args) {
        SyncchnorizedDemo demo = new SyncchnorizedDemo();
        demo.new ReadWriteThread(true).start();
        demo.new ReadWriteThread(false).start();
    }
}
