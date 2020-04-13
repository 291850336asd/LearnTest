package com.meng.datastrcture;

public class DoubleTreeTest {

    public static void main(String[] args) {
        //中序排序
        DoubleTree dt = new DoubleTree();
        dt.add(5);
        dt.add(2);
        dt.add(7);
        dt.add(4);
        dt.add(6);
        dt.add(9);
        dt.add(3);
        dt.add(6);
        dt.add(8);
        dt.show();
    }
}
