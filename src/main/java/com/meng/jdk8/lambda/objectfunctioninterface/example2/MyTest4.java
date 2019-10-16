package com.meng.jdk8.lambda.objectfunctioninterface.example2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyTest4 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("zhangsan", "lisi", "wangwu");
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });
        Collections.sort(list, (o1, o2) -> {
            return o2.compareTo(o1); //降序
        });
        Collections.sort(list, (o1, o2) -> {return o2.compareTo(o1);});
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
        Collections.sort(list, Comparator.naturalOrder());//升序
        Collections.sort(list, Comparator.reverseOrder());//降序
        System.out.println(list);
    }

}
