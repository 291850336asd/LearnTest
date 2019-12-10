package com.meng.exapmle.concurrent.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        testThreadTime();
        testThreadPoolTime();

    }

    private static void testThreadTime() throws InterruptedException {
        Long start=System.currentTimeMillis();
        final Random random=new Random();
        final List<Integer> list=new ArrayList<Integer>();
        for (int i=0;i<100000;i++){
            Thread thread=  new Thread(){
                @Override
                public void run() {
                    list.add(random.nextInt());

                }
            };
            thread.start();
            thread.join();
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(list.size());
    }

    private static void testThreadPoolTime() throws InterruptedException {
        Long start=System.currentTimeMillis();
        final Random random=new Random();
        final List<Integer> list=new ArrayList<Integer>();
        ExecutorService executeService = Executors.newFixedThreadPool(1);
        for (int i=0;i<100000;i++){
            executeService.submit(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        executeService.shutdown();
        executeService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(list.size());
    }


}
