package net.dzirt;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class FileProcessingThread implements Runnable {
    private Path outputFilePath; //One output file corresponds to one input file
    private File currentFile;

    public FileProcessingThread(Path outputFilePath, File currentFile) {
        this.outputFilePath = outputFilePath;
        this.currentFile = currentFile;
    }

    @Override
    public void run() {
        CSVReader csvReader = new CSVReader(currentFile);           //Creating reader for one input csv file
        List list = csvReader.readLinesOfFile();        //Getting list of lines from csv file

        LinesHandler linesHandler = new LinesHandler(list);         //Creating object to handle list of lines
        DateUsers dateUsers = linesHandler.getDateUsersList();      //Handling list to get map with Dates and lists of users-urls-avg times

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath + System.getProperty("file.separator") + "avg_" + currentFile.getName()), "utf-8"))) { //Creating output file with "avg_" prefix, and pu it into output folder
            writer.write("ID user, URL, Average \n");
            dateUsers.getDateUsers().forEach((key, value) -> { //Getting Dates and lists of values from map
                try {
                    writer.write(key.toString() + "\n");    //Write Date to file
                    List<UserUrlTime> userUrlTimeList = ((OneDateArray) value).getUserUrlTimeList(); //Creating list from value of map
                    for (UserUrlTime userUrlTime : userUrlTimeList) {
                        writer.write(userUrlTime.getUserUrl() + ", " + userUrlTime.getTime() + "\n"); // Write to file list of user-url-avgtimes
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
