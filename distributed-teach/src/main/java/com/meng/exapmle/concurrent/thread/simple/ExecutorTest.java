package com.meng.exapmle.concurrent.thread.simple;

import java.util.concurrent.Executor;

public class ExecutorTest implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }

    public static void main(String[] args) {
        new ExecutorTest().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("猴子");

            }
        });
    }
}
