package net.dzirt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DateUsers {
    private Map dateUsers = new HashMap<String, OneDateArray>();

    public void addDateUser(String date, String userID, String url, long time) {
        OneDateArray oneDateArray = (OneDateArray) dateUsers.get(date);
        if(oneDateArray == null) {
            OneDateArray oda = new OneDateArray(date);
            oda.addUserUrlTime(userID,url,time);
            dateUsers.put(date,oda);
        } else {
            OneDateArray oda = (OneDateArray) dateUsers.get(date);
            oda.addUserUrlTime(userID,url,time);

        }
//        UserUrls userUrls = (UserUrls) dateUsers.get(date);
//        if (userUrls == null){
//            UserUrls uu = new UserUrls();
//            uu.addUserUrl(userID, url, time);
//            dateUsers.put(date,uu);
//        } else {
//            UserUrls uu = (UserUrls) dateUsers.get(date);
//            uu.addUserUrl(userID, url, time);
//        }

    }
//    public ArrayList<String> getDateArray

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
