package net.dzirt;

import java.text.SimpleDateFormat;
import java.util.*;


public class LinesHandler {
    private List<LineOfFile> inputList;

    public LinesHandler(List<LineOfFile> inputList) {
        this.inputList = inputList;
    }

    public DateUsers getDateUsersList() {
        DateUsers dateUsers = new DateUsers();

        for (int i = 0; i < inputList.size(); i++) {
            int dayStart = TimeStampHelper.timeStampToDayOfYear(inputList.get(i).getTimeStamp());
            int dayFinish = TimeStampHelper.timeStampToDayOfYear(inputList.get(i).getTimeStamp() + inputList.get(i).getTime());

            if(dayFinish == dayStart) {
                Date t = TimeStampHelper.timeStampToDate(inputList.get(i).getTimeStamp());
                SimpleDateFormat dateFormat  = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String date = dateFormat.format(t);
                dateUsers.addDateUser(  date,
                        inputList.get(i).getId(),
                        inputList.get(i).getUrl(),
                        inputList.get(i).getTime()
                );
            } else {
                List<Long> timesList = new ArrayList<>();
                Date startDay = TimeStampHelper.getDayMonthYear(inputList.get(i).getTimeStamp());
                Date nextDay = new Date(startDay.getTime());
                nextDay.setDate(nextDay.getDate() + 1);
                Long time = nextDay.getTime() - inputList.get(i).getTimeStamp()*1000;
                timesList.add(time / 1000);

                if (dayFinish - dayStart > 1) {
                    for (int j = dayStart+1; j < dayFinish; j++){
                        timesList.add(TimeStampHelper.SECONDS_IN_DAY);
                    }
                }

                Date finishDay = TimeStampHelper.getDayMonthYear(inputList.get(i).getTimeStamp() + inputList.get(i).getTime());
                time = (inputList.get(i).getTimeStamp() + inputList.get(i).getTime()) * 1000 - finishDay.getTime();
                timesList.add(time / 1000);

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
