package net.dzirt;

import java.text.SimpleDateFormat;
import java.util.*;


public class LinesHandler {
    private List<LineOfFile> inputList;

    public LinesHandler(List<LineOfFile> inputList) {
        this.inputList = inputList;
    }

    public DateUsers getDateUsersList() {
        DateUsers dateUsers = new DateUsers(); //object with map date - lines

        for (LineOfFile anInputList : inputList) {  //For every Line
            int dayStart = TimeStampHelper.timeStampToDayOfYear(anInputList.getTimeStamp());           //Calculate start date (when user open url)
            int dayFinish = TimeStampHelper.timeStampToDayOfYear(anInputList.getTimeStamp() + anInputList.getTime());//Calculate finish date (when user left url)

            if (dayFinish == dayStart) { //if user connect and left in same date
                Date t = TimeStampHelper.timeStampToDate(anInputList.getTimeStamp());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String date = dateFormat.format(t); //Creating date with certain format
                dateUsers.addDateUser(date,
                        anInputList.getId(),
                        anInputList.getUrl(),
                        anInputList.getTime()); //Add this line in dateUsers map
            } else { //else (if user connect and left in different days)
                List<Long> timesList = new ArrayList<>();
                Date startDay = TimeStampHelper.getDayMonthYear(anInputList.getTimeStamp());
                Date nextDay = new Date(startDay.getTime());
                nextDay.setDate(nextDay.getDate() + 1);
                Long time = nextDay.getTime() - anInputList.getTimeStamp() * 1000;
                timesList.add(time / 1000); //add first day

                if (dayFinish - dayStart > 1) {
                    for (int j = dayStart + 1; j < dayFinish; j++) { //add all intermediate days
                        timesList.add(TimeStampHelper.SECONDS_IN_DAY);
                    }
                }

                Date finishDay = TimeStampHelper.getDayMonthYear(anInputList.getTimeStamp() + anInputList.getTime());
                time = (anInputList.getTimeStamp() + anInputList.getTime()) * 1000 - finishDay.getTime();
                timesList.add(time / 1000); // add last day

                Date d = TimeStampHelper.timeStampToDate(anInputList.getTimeStamp());
                int s = 0;
                for (int k = dayStart; k <= dayFinish; k++) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                    String date = dateFormat.format(d);
                    dateUsers.addDateUser(date,
                            anInputList.getId(),
                            anInputList.getUrl(),
                            timesList.get(s)
                    );
                    s++;
                    d.setDate(d.getDate() + 1);
                }
            }
        }
        return dateUsers;
    }


}
