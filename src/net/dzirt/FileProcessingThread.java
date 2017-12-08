package net.dzirt;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileProcessingThread implements Runnable {
    private Path inputFilePath;
    private Path outputFilePath;
    private String inputFileName = "//file1.csv";


    public FileProcessingThread(Path inputFilePath, Path outputFilePath){
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {

        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Path inputPath = propertiesLoader.getInputPath();
        Path outputPath = propertiesLoader.getOutputPath();
        String inputFileName = "file1.csv";

        CSVReader csvReader = new CSVReader(Paths.get(inputPath + "\\" + inputFileName));
        List<LineOfFile> list = csvReader.readLinesOfFile();
        LinesTreatment linesTreatment = new LinesTreatment(list); //TODO: rename to ListHandler??
        DateUsers dateUsers = linesTreatment.getDateUsersList();

        Map map;

        //File outputFile = new File(outputPath + "\\avg_" + inputFileName);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputPath + "\\avg_" + inputFileName), "utf-8"))) {
            map = dateUsers.getDateUsers();
            map.forEach((key, value) -> {
                System.out.println(key + " " + value);
                try {
                    writer.write(key.toString() + "\n");
                    List<UserUrlTime> userUrlTimeList = ((OneDateArray)value).getUserUrlTimeList();
                    for (UserUrlTime userUrlTime : userUrlTimeList) {
                        //System.out.println(userUrlTime);
                        writer.write(userUrlTime.getUser() + ", " + userUrlTime.getUrl() + ", " + userUrlTime.getTime() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            //writer.write(dateUsers.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(dateUsers);

    }
}
