package net.dzirt;

import java.util.*;
public class TimeStampHelper {
    public static final long SECONDS_IN_DAY = 24*60*60;


    public static int timeStampToDayOfYear (long timeStamp){
        Date date = new Date(timeStamp * 1000);
        Calendar calendar = new GregorianCalendar(date.getYear(),date.getMonth(),date.getDate());
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

//    public static long timeStampToAbsoluteDay(long timeStamp) {
//        return timeStamp / (24*60*60);
//    }

    public static Date timeStampToDate (long timeStamp){
        return new Date(timeStamp * 1000);
    }

    public static Date getDayMonthYear ( long timeStamp) {
        Date date = new Date(timeStamp * 1000);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }
//
//    public static void main(String[] args) {
//        long t = 1455839970;
//
//        System.out.println(timeStampToDayOfYear(t));
//        System.out.println(timeStampToDayOfYear(t + 100000));
//        System.out.println(timeStampToAbsoluteDay(t));
//        System.out.println(timeStampToAbsoluteDay(t + 100000));
//
//    }
}
