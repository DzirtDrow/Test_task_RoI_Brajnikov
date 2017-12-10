package net.dzirt;

import java.util.*;

public class DateUsers {

    private Map<String, OneDateArray> dateUsers = new TreeMap<>();
    public void addDateUser(String date, String userID, String url, long time) {
        OneDateArray oneDateArray = dateUsers.get(date);
        if(oneDateArray == null) {
            OneDateArray oda = new OneDateArray(date);
            oda.addUserUrlTime(userID,url,time);
             dateUsers.put(date, oda);
        } else {
            OneDateArray oda = dateUsers.get(date);
            oda.addUserUrlTime(userID,url,time);
        }
    }

    @Override
    public String toString() {

        return "DateUsers{" +
                "dateUsers=" + dateUsers +
                '}';
    }
    public Map getDateUsers() {
        return dateUsers;
    }
}
