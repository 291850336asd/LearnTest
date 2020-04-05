package com.meng.datastrcture;

/**
 * 二分查找
 */
public class Test3二分查找 {

    public static void main(String[] args) {
        int[] ints={2,4,7,8,10,12,16,19,20,21};
        int num = 17;
        int s=0;//开始位置
        int e = ints.length-1;//结束位置
        int m = 0;//中间位置
        while(s<=e)
        {
            m=(s+e)/2;
            if(num==ints[m])
            {
                System.out.println("找到:"+m);
                return;
            }
            else if(num<ints[m])//左边
            {
                e=m-1;
            }
            else//右边
            {
                s=m+1;
            }
        }
        System.out.println("不存在");
    }

}
