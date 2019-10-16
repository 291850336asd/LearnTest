package com.meng.jdk8.joda;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class Test2 {

    //标准UTC：yyyy-MM-ddTHH:mm:ss.SSSZ 2014-11-04T09:56:56.324Z
    public static Date convertUTC2Date(String utcDate){
        try {
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        }catch (Exception e){
            return null;
        }
    }

    public static String convertDate2UTC(Date javaDate){
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return  dateTime.toString();
    }

    public static String convertDate2LocalByDateFormat(Date javaDate, String dateFormat){
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }

    public static void main(String[] args) {
        System.out.println(convertUTC2Date("2014-11-04T09:22:54.876Z"));
        System.out.println(convertDate2UTC(new Date()));
        System.out.println(convertDate2LocalByDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

}
