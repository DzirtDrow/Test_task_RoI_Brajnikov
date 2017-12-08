package net.dzirt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LinesTreatment {
    private List<LineOfFile> inputList;

    public LinesTreatment(List<LineOfFile> inputList) {
        this.inputList = inputList;
    }

//    public OneDateArray getOneDateArray (){
//        //OneDateArray oneDateArray = new OneDateArray();
//        //return oneDateArray;
//    }

    public DateUsers getDateUsersList() {
        DateUsers dateUsers = new DateUsers();

        for (int i = 0; i < inputList.size(); i++) {


            Date t = TimeStampHelper.timeStampToDate(inputList.get(i).getTimeStamp());
            //System.out.println(" t[" + i + "] - " + t);

            SimpleDateFormat dateFormat  = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String date = dateFormat.format(t);
            //System.out.println(i + " - " + date);



            dateUsers.addDateUser(  date,
                                    inputList.get(i).getId(),
                                    inputList.get(i).getUrl(),
                                    inputList.get(i).getTime()
            );




        }

        return dateUsers;
    }


}
