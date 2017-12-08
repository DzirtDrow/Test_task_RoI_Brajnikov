package net.dzirt;

public class LineOfFile {
    long timeStamp;
    String id;
    String url;
    String time;


    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LineOfFile{" +
                "timeStamp='" + timeStamp + '\'' +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
