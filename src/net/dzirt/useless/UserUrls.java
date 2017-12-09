package net.dzirt.useless;

import net.dzirt.UrlTimeArray;

import java.util.HashMap;
import java.util.Map;

public class UserUrls {
    private Map urlUserUrlMap = new HashMap<String, UrlTimeArray>();

    public void addUserUrl (String userID, String url, long time){
        UrlTimeArray urlTimeArray = (UrlTimeArray) urlUserUrlMap.get(userID);

        if(urlTimeArray == null){
            UrlTimeArray utar = new UrlTimeArray();
            utar.addUrlTime(new UrlTime(url,time));
            urlUserUrlMap.put(userID,utar);
        } else {
            UrlTimeArray utar = (UrlTimeArray) urlUserUrlMap.get(userID);
            utar.addUrlTime(new UrlTime(url,time));
        }
    }




    @Override
    public String toString() {
        return "UserUrls{" +
                "urlUserUrlMap=" + urlUserUrlMap +
                '}';
    }
}
