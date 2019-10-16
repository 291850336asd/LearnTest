package com.meng.jdk8.streamtheory;

public class AutoCloseTest implements AutoCloseable{


    public static void main(String[] args) {
        try(AutoCloseTest autoCloseTest = new AutoCloseTest()){
            autoCloseTest.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSomething(){
        System.out.println("something invoked");
    }


    @Override
    public void close() throws Exception {
        System.out.println("close invoked");
    }
}
