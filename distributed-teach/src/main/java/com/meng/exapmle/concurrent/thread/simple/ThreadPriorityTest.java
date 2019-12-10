package com.meng.exapmle.concurrent.thread.simple;

public class ThreadPriorityTest {

    public static class WukongThread extends Thread{
        private String name;
        public WukongThread(String name){
            this.name=name;
        }

        int count=0;
        public void run(){
            while(true){
                synchronized(ThreadPriorityTest.class) {
                    count++;
                    if(count>10000000) {
                        System.out.println(name + "执行完成");
                        break;
                    }
                }
            }
        }
    }
    public static class HouziThread extends Thread{

        private String name;
        public HouziThread(String name){
            super.setName(name);
        }

        int count=0;
        public void run(){
            while(true){
                synchronized(ThreadPriorityTest.class) {
                    count++;
                    if (count > 10000000) {
                        System.out.println(this.getName() + "执行完成");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread high=new WukongThread("高优先级别");
        HouziThread low=new HouziThread("低优先级别");
        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
        high.start();
    }
}
