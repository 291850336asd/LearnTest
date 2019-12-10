package com.meng.exapmle.concurrent.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ScheduleExecutorsService {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(1);

        //等同
        ScheduledExecutorService scheduledExecutorService2= new ScheduledThreadPoolExecutor(1);

        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("猴子1");
            }
        },0,5, TimeUnit.SECONDS);


        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("猴子2");
                    throw  new RuntimeException();
                } catch (RuntimeException e) {
                    //e.printStackTrace();
                }

            }
        },0,5, TimeUnit.SECONDS);
    }
}
