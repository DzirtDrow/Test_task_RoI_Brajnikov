package net.dzirt.useless;

public class UrlTime {
    private String url;
    private long time;

    public UrlTime(String url, long time) {
        this.url = url;
        this.time = time;
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
        return "UrlTime{" +
                "url='" + url + '\'' +
                ", time=" + time +
                '}';
    }
}
