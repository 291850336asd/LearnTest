package com.meng.exapmle.zookeeper.curator.lock;


public class LockTest implements  Runnable {
    static  int i=0;
    CuratorInterProcessMutex curatorInterProcessMutex=new CuratorInterProcessMutex("/root");

    @Override
    public void run() {
        try {
            curatorInterProcessMutex.acquire();
            for(int j=0;j<1000000;j++){
                i++;
            }
            curatorInterProcessMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest=new LockTest();
        Thread thread=new Thread(lockTest);
        Thread thread2=new Thread(lockTest);
        thread.start();thread2.start();
        thread.join();thread2.join();
        System.out.println(i);

    }



}
