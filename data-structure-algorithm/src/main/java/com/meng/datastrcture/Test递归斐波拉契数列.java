package com.meng.datastrcture;

public class Test递归斐波拉契数列 {
    public static void main(String[] args) {
        //斐波拉契数列1,1，2,3,5,8.....

        // 循环实现
		int num1=1;
		int num2 = 1;
		int num3=0;
		int n = 6;
		for(int i=3;i<=n;i++)
		{
			num3=num1+num2;
			num1=num2;
			num2=num3;
		}
		System.out.println(num3);
        System.out.println(run(6));
    }

    static int run(int n)
    {
        if(n==1||n==2)
        {
            return 1;
        }
        else
        {
            return run(n-1)+run(n-2);
        }
    }
}
