package net.dzirt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OneDateArray {
    private String date;
    //private List<String> stringArrayList = new ArrayList<>();
    private List<UserUrlTime> userUrlTimeList = new ArrayList<>();

    public OneDateArray(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

//    public List<String> getStringArrayList() {
//        return stringArrayList;
//    }

    public List<UserUrlTime> getUserUrlTimeList() {
        return userUrlTimeList;
    }

    public void addUserUrlTime(String user, String url, long time){
        userUrlTimeList.add(new UserUrlTime(user,url,time));

    }


    @Override
    public String toString() {
        return "OneDateArray{" +
                "date='" + date + '\'' +
                ", userUrlTimeList=" + userUrlTimeList +
                '}';
    }
}
