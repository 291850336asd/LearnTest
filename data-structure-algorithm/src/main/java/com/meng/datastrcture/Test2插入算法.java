package com.meng.datastrcture;

/**
 * 插入算法
 */
public class Test2插入算法 {

	public static void main(String[] args) {

		int[] ints = {7,3,3,8,2,1,9,4,5};

		// i:1到最后
		for(int i=1;i<ints.length;i++)
		{
			if(ints[i]<ints[i-1])
			{
				for(int j=0;j<i;j++)//找到插入的位置
				{
					if(ints[i]<ints[j])
					{
						int temp = ints[i];
						for(int k=i-1;k>=j;k--)
						{
							ints[k+1]=ints[k];//向后移动
						}
						ints[j]=temp;
						break;
					}
				}
			}
		}

		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + " ");
		}
		System.out.println();
		System.out.println("---------------");

		ints = new int[]{ 7,3,3,8,2,1,9,4,5 };
		for (int i = 1; i < ints.length; i++) {
			int temp = ints[i];
			for (int j = i-1; j >=0 ; j--) {
				if(ints[j] > temp){
					ints[j+1] = ints[j];
					ints[j] = temp;
				}
			}
		}

		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + " ");
		}
	}

}
