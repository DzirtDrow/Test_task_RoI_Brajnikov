package net.dzirt;

public class UserUrlTime {
    private String userUrl;
    private long time;

    public UserUrlTime(String userUrl, long time) {
        this.userUrl = userUrl;
        this.time = time;
    }


    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String url) {
        this.userUrl = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserUrlTime{" +
                ", user url='" + userUrl + '\'' +
                ", time=" + time +
                '}';
    }
}
