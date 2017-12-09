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

        for (int i = 0; i < inputList.size(); i++) {  //For every Line
            int dayStart = TimeStampHelper.timeStampToDayOfYear(inputList.get(i).getTimeStamp()); //Calculate start date (when user open url)
            int dayFinish = TimeStampHelper.timeStampToDayOfYear(inputList.get(i).getTimeStamp() + inputList.get(i).getTime());//Calculate finish date (when user left url)

            if(dayFinish == dayStart) { //if user connect and left in same date
                Date t = TimeStampHelper.timeStampToDate(inputList.get(i).getTimeStamp());
                SimpleDateFormat dateFormat  = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String date = dateFormat.format(t); //Creating date with certain format
                dateUsers.addDateUser(  date,
                        inputList.get(i).getId(),
                        inputList.get(i).getUrl(),
                        inputList.get(i).getTime()); //Add this line in dateUsers map
            } else { //else (if user connect and left in different days)
                List<Long> timesList = new ArrayList<>();
                Date startDay = TimeStampHelper.getDayMonthYear(inputList.get(i).getTimeStamp());
                Date nextDay = new Date(startDay.getTime());
                nextDay.setDate(nextDay.getDate() + 1);
                Long time = nextDay.getTime() - inputList.get(i).getTimeStamp()*1000;
                timesList.add(time / 1000); //add first day

                if (dayFinish - dayStart > 1) {
                    for (int j = dayStart+1; j < dayFinish; j++){ //add all intermediate days
                        timesList.add(TimeStampHelper.SECONDS_IN_DAY);
                    }
                }

                Date finishDay = TimeStampHelper.getDayMonthYear(inputList.get(i).getTimeStamp() + inputList.get(i).getTime());
                time = (inputList.get(i).getTimeStamp() + inputList.get(i).getTime()) * 1000 - finishDay.getTime();
                timesList.add(time / 1000); // add last day

                Date d = TimeStampHelper.timeStampToDate(inputList.get(i).getTimeStamp());
                int s = 0;
                for (int k = dayStart; k<= dayFinish; k++ ) {
                    SimpleDateFormat dateFormat  = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                    String date = dateFormat.format(d);
                    dateUsers.addDateUser(  date,
                            inputList.get(i).getId(),
                            inputList.get(i).getUrl(),
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
