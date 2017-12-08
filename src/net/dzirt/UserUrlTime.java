package net.dzirt;

public class UserUrlTime {
    private String user;
    private String url;
    private long time;

    public UserUrlTime(String user, String url, long time) {
        this.user = user;
        this.url = url;
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
                "user='" + user + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                '}';
    }
}
