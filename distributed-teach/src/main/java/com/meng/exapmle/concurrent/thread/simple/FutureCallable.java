package com.meng.exapmle.concurrent.thread.simple;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        StringBuffer sb=new StringBuffer();
        for (int i =0;i<3;i++){
            sb.append("猴子").append(i);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task=new FutureTask(new FutureCallable(){

        });
        new Thread(task).start();
      //  task.cancel(true);
        System.out.println(task.get());


    }
}
