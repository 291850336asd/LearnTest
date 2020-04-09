package com.meng.datastrcture;

public class Test快速排序算法 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //快速排序

        int[] ints = {5,3,7,8,2,9,4,6};

        sort(ints,0,ints.length-1);

        for (int j2 = 0; j2 < ints.length; j2++) {
            System.out.println(ints[j2]);
        }
    }

    static void sort(int[] ints,int s,int e)
    {
        if(s<e)
        {
            int index = sortUnit(ints,s,e);
            sort(ints,s,index-1);//左边
            sort(ints,index+1,e);//右边
        }
    }

    static int sortUnit(int[] ints,int s,int e)
    {
        int num = ints[s];//标杆
        int i=s;//开始位置
        int j=e;//结束位置
        while(i<j)
        {
            while(i<j)
            {
                if(ints[j]<num)//j负责找小的,扔给i
                {
                    ints[i]=ints[j];
                    break;
                }
                j--;
            }
            while(i<j)
            {
                if(ints[i]>=num)//i负责找大的,扔给j
                {
                    ints[j]=ints[i];
                    break;
                }
                i++;
            }
        }
        ints[i]=num;
        return i;
    }
}
