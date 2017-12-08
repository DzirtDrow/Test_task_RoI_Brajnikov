package net.dzirt;

import java.time.*;
import java.util.*;
public class TimeStampHelper {

    public static int timeStampToDayOfYear (long timeStamp){
        Date date = new Date(timeStamp * 1000);
        Calendar calendar = new GregorianCalendar(date.getYear(),date.getMonth(),date.getDate());
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static Date timeStampToDate (long timeStamp){
        Date date = new Date(timeStamp * 1000);
        return date;
    }


    public static void main(String[] args) {
        long timeStamp = 1280512800;
        Date d1 = new Date(timeStamp * 1000);
        long t = 24*60*60*200;

        System.out.println(d1);
        Date d2 = new Date((timeStamp  + t) * 1000);
        if (d2.getDay()>d1.getDay()){
            System.out.println("Есть!");
        }
        System.out.println(d2);


        Calendar c1 = new GregorianCalendar(d1.getYear(),d1.getMonth(),d1.getDate());
        System.out.println(c1.get(Calendar.DAY_OF_YEAR));

        Calendar c2 = new GregorianCalendar(d2.getYear(),d2.getMonth(),d2.getDate());
        System.out.println(c2.get(Calendar.DAY_OF_YEAR));

        System.out.println(c2.get(Calendar.DAY_OF_YEAR)-c1.get(Calendar.DAY_OF_YEAR));
        long x = d1.getTime()/(24*60*60);
        System.out.println(x);

    }
}
