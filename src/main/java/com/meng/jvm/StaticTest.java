package com.meng.jvm;

public class StaticTest {

    private static  int b = 10;

    static {
        a = 1;
        System.out.println(b);
        // 只能写不能读
        // System.out.println(a);
    }

    private static  int a = 10;

    public static void main(String[] args) {
        System.out.println(a);
    }

}
