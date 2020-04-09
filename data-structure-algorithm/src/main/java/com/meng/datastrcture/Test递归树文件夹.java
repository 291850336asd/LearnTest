package com.meng.datastrcture;

import java.io.File;

public class Test递归树文件夹 {
    public static void main(String[] args) {
        //树遍历：文件夹
        play(new File("D:\\xxx "));
    }

    static void play(File file)
    {
        //获取当前文件夹下所有子文件夹/文件
        File[] files = file.listFiles();
        for(int i =0;i<files.length;i++)
        {
            if(files[i].isFile())//文件
            {
                System.out.println(files[i].getName());
            }
            else
            {
                play(files[i]);
            }
        }
    }

}
