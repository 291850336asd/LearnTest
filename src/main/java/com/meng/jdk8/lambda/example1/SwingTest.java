package com.meng.jdk8.lambda.example1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {

    public static void main(String[] args) {

//        test1();
//        test2();
    }

    private static void test2() {
        JFrame jFrame = new JFrame("jframe");
        JButton jButton = new JButton("btn");
        jButton.addActionListener(e -> System.out.println("click"));
        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void test1() {
        JFrame jFrame = new JFrame("jframe");
        JButton jButton = new JButton("btn");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click");
            }
        });
        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
