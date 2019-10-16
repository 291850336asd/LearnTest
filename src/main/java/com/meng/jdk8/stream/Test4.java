package com.meng.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test4 {

    public static void main(String[] args) {
        //流构成 ：  源  零个或多个中间操作  终止操作
        //流操作的分类：  1.惰性求值 2.及早求值

        String[] oldStrings = new String[]{"hello","world","helloworld"};
        Stream<String> stream1 = Stream.of(oldStrings);
       // String[] stringArray =  stream1.toArray(length -> new String[length]);
        String[] stringArray =  stream1.toArray(String[]::new);
        stringArray[0] = "sdd";
        Arrays.asList(oldStrings).forEach(System.out::println);
        Arrays.asList(stringArray).forEach(System.out::println);
        System.out.println("--------------");
        List<String> list2 = Stream.of(stringArray).collect(Collectors.toList());
        list2.forEach(System.out::println);
         list2 = Stream.of(stringArray).collect(ArrayList::new,
                (arrayList,str)->arrayList.add(str), (arratList1, arrayList2) -> arratList1.addAll(arrayList2));
        list2.forEach(System.out::println);
        list2 = Stream.of(stringArray).collect(ArrayList::new,
                ArrayList::add, (arratList1, arrayList2) -> arratList1.addAll(arrayList2));
        list2.forEach(System.out::println);
        list2 = Stream.of(stringArray).collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        list2.forEach(System.out::println);

        String concat = Stream.of(stringArray).collect(StringBuilder::new, StringBuilder::append,
                StringBuilder::append).toString();
        System.out.println(concat);
        System.out.println("--------------");
        list2 = Stream.of(stringArray).collect(Collectors.toCollection(ArrayList::new));
        list2.forEach(System.out::println);
        System.out.println("---------------");
        Set<String> stringSet = Stream.of(stringArray).collect(Collectors.toCollection(TreeSet::new));
        stringSet.forEach(System.out::println);
        concat = Stream.of(stringArray).collect(Collectors.joining()).toString();
        System.out.println(concat);
        concat = Stream.of(stringArray).collect(Collectors.joining(",")).toString();
        System.out.println(concat);
        concat = Stream.of(stringArray).collect(Collectors.joining(",", "--","..")).toString();
        System.out.println(concat);
    }

}
