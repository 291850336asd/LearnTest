package com.meng.datastrcture;

import org.joda.time.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {

        System.out.println((Math.abs("E304000000040593167".hashCode())&7) + 1);

//        System.out.println(LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN));
//        System.out.println(LocalDateTime.parse("2020-04-24 01:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//
//        System.out.println(Integer.toBinaryString(~5));
//        System.out.println(Integer.MAX_VALUE - Integer.parseInt("1111111111111111111111111111010", 2));

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("12", "12");
    }
}