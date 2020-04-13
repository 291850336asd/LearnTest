package com.meng.datastrcture;

import java.util.Arrays;

/**
 *  （1）从数组索引1的位置开始
 *  （2）找最大数
 *        1.每个二叉树都是父子节点
 *        2.每个最小的二叉树最大的子节点若大于父节点，则子节点和父节点交换 替换原则：从下往上替换
 *  （3）根节点与最后一个节点交换
 *
 *   代码实现思路：
 *   （1）找出父节点个数 《数组长度-1）/2
 *   （2）找到父节点索引位置即找到父节点，从一步中找到的父节点个数即父节点的位置，索引从1开始（1-）父节点个数
 *   （3）根据父亲的索引算出  左子节点：2*（父索引） 右子节点：2*（父索引）+ 1
 */
public class Test二叉树数组转二叉树 {

    public static void main(String[] args) {
        int[] ints = {5, 7, 2, 5, 8, 9, 3, 4, 6};
        int size  = ints.length;

        while (size >2){
            //循环所有的父节点（从后向前循环）
            for(int i = (size-1)/2; i>=1; i-- ){
                //先找到最大的子节点
                int maxIndex = i *2; //假设最大的节点是左子节点
                //右子节点存在切比左子节点大
                if(i*2 + 1 < size && ints[i*2 +1] > ints[i*2]){
                    maxIndex = i*2 +1;
                }
                //最大的子节点与父节点比较
                if(ints[maxIndex] > ints[i]){
                    int temp = ints[maxIndex];
                    ints[maxIndex] = ints[i];
                    ints[i] = temp;
                }

            }
            //跟节点与最后一个几点交换
            int temp = ints[1];
            ints[1] = ints[size -1];
            ints[size -1] = temp;

            size--;
        }

        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }

}
