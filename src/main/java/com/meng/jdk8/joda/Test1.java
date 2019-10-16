package com.meng.jdk8.joda;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;


public class Test1 {

    public static void main(String[] args) {
        DateTime today = new DateTime();
        DateTime tomorrow = today.plusDays(1);
        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));
        System.out.println("------------------------");
        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd"));
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);
        System.out.println("-----------------");
        localDate = localDate.plusMonths(3).dayOfMonth().withMinimumValue();
        System.out.println(localDate);
        localDate = new LocalDate().plusMonths(3).dayOfMonth().withMaximumValue();
        System.out.println(localDate);
        System.out.println("---------------");
        DateTime dateTime = new DateTime().plusYears(-2).monthOfYear().setCopy(3).dayOfMonth().withMinimumValue();
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
    }

}
