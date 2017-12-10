package net.dzirt;

import java.util.*;
public class TimeStampHelper {
    public static final long SECONDS_IN_DAY = 24*60*60;


    public static int timeStampToDayOfYear (long timeStamp){
        Date date = new Date(timeStamp * 1000);
        Calendar calendar = new GregorianCalendar(date.getYear(),date.getMonth(),date.getDate());
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

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
}
