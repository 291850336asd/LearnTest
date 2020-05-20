package com.meng.example.redis;


import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

public class BloomFilterTest {


    private static int size = 1000000;

    /**
     * 集合只能添加数据，不能获取更新删除数据
     */
    private static BloomFilter<Integer> bloomFilter =BloomFilter.create(Funnels.integerFunnel(), size, 0.01);
    public static void main(String[] args) {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<Integer>(1000);
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
        list = new ArrayList<Integer>(20000);
        for (int i =  10000; i <  20000; i++) {
            if (!bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }
}
