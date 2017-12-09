package net.dzirt;

import java.util.*;

public class OneDateArray {
    private String date;
    private List<UserUrlTime> userUrlTimeList = new ArrayList<>();

    public OneDateArray(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void countAvgTimes(){
        Map<String,AverageCount> userUrlAvg = new TreeMap<>(); //creating map with unique pair "user, url" as key
        for (int i = 0; i < userUrlTimeList.size(); i++) {
            String key = userUrlTimeList.get(i).getUserUrl().replace(" ", "");
            if(userUrlAvg.containsKey(key))
            {
                userUrlAvg.get(key).avg =  userUrlAvg.get(key).avg + userUrlTimeList.get(i).getTime();
                userUrlAvg.get(key).countInc();
            } else {
                userUrlAvg.put(key, new AverageCount(userUrlTimeList.get(i).getTime() , 1));
            }
        }
        userUrlTimeList = new ArrayList<UserUrlTime>();  //recreating this array for placing avg times
        userUrlAvg.forEach((key, value) -> {
            value.setAvg(value.getAvg() / value.getCount()); //counting average time for every user-url pair

            userUrlTimeList.add(new UserUrlTime(key, value.getAvg()));  //add avg times for every user-url pair

        });

    }

    public List<UserUrlTime> getUserUrlTimeList() {
        countAvgTimes();
        return userUrlTimeList;
    }

    public void addUserUrlTime(String user, String url, long time){
        String userUrl = user.replace(" ", "") + ", " + url.replace( " ", "");
        userUrlTimeList.add(new UserUrlTime(userUrl,time));

    }


    @Override
    public String toString() {
        return "OneDateArray{" +
                "date='" + date + '\'' +
                ", userUrlTimeList=" + userUrlTimeList +
                '}';
    }
}

class AverageCount{
    long avg = 0;
    int count = 0;

    public void countInc(){
        count++;
    }

    public AverageCount(Long avg, int count) {
        this.avg = avg;
        this.count = count;
    }

    public Long getAvg() {
        return avg;
    }

    public void setAvg(Long avg) {
        this.avg = avg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AverageCount{" +
                "avg=" + avg +
                ", count=" + count +
                '}';
    }
}
