package net.dzirt;

import net.dzirt.useless.UrlTime;

import java.util.ArrayList;
import java.util.List;

public class UrlTimeArray {
    List urlTimeArray = new ArrayList<UrlTime>();
    public void addUrlTime (UrlTime urlTime){
        urlTimeArray.add(urlTime);
    }


    @Override
    public String toString() {
        return "UrlTimeArray{" +
                "urlTimeArray=" + urlTimeArray +
                '}';
    }
}

