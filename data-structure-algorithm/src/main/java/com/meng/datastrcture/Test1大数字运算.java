package com.meng.datastrcture;

import java.math.BigDecimal;

/**
 * 大数字运算
 */
public class Test1大数字运算 {

    public static void main(String[] args) {

		//n!阶乘=1*2*3*4*5...n
        int n=100;
		BigDecimal num1  = new BigDecimal(1); //可以
		//double 精度不够
		//超出int的范围
		for(int i=1; i<=n; i++)
		{
			num1=num1.multiply(new BigDecimal(i));
		}
		System.out.println(num1);
		System.out.println("--------------");
		//732*16
		int[] ints = new int[6];
		ints[ints.length-1] = 2;
		ints[ints.length-2] = 7;
		ints[ints.length-3] = 3;
		int num = 18;
		//计算每一位的值
		for (int i = 0; i < ints.length; i++) {
			ints[i] = ints[i] * num;
		}
		//进和留
		for (int i = ints.length - 1; i > 0 ; i--) {
			ints[i-1]+=ints[i]/10;
			ints[i]=ints[i]%10;
		}
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i]);
		}
		System.out.println();
		System.out.println("--------------");
		ints = new int[200];
		ints[ints.length-1]=1;
		for(int i=1;i<=n;i++)
		{
			ints=factorial(ints,i);
		}


		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i]);
		}


    }

	static int[] factorial(int[] ints,int num)
	{
		for (int i = 0; i < ints.length; i++) {
			ints[i]*=num;
		}
		//进和留
		for(int i=ints.length-1;i>0;i--)
		{
			ints[i-1]+=ints[i]/10;
			ints[i]=ints[i]%10;
		}
		return ints;
	}


}
