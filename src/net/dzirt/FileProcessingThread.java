package net.dzirt;

import java.io.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileProcessingThread implements Runnable {
    private Path inputFilePath;
    private Path outputFilePath;
    private String inputFileName = "//file1.csv";
    private File currentFile;


    public FileProcessingThread(Path inputFilePath, Path outputFilePath){
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public FileProcessingThread(Path inputFilePath, Path outputFilePath, String inputFileName) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
        this.inputFileName = inputFileName;
    }

    public FileProcessingThread(Path outputFilePath, File currentFile) {
        this.outputFilePath = outputFilePath;
        this.currentFile = currentFile;
    }

    @Override
    public void run() {

        //String inputFileName = "file1.csv";
        System.out.println("обработка файла " + currentFile.toString() + " началась");

        CSVReader csvReader = new CSVReader(currentFile);
        List<LineOfFile> list = csvReader.readLinesOfFile();
        LinesTreatment linesTreatment = new LinesTreatment(list); //TODO: rename to ListHandler??
        DateUsers dateUsers = linesTreatment.getDateUsersList();

        Map map;

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputFilePath + "\\avg_" + currentFile.getName()), "utf-8"))) {
            map = dateUsers.getDateUsers();
            map.forEach((key, value) -> {
                //System.out.println(key + " " + value);
                try {
                    writer.write(key.toString() + "\n");
                    List<UserUrlTime> userUrlTimeList = ((OneDateArray)value).getUserUrlTimeList();
                    for (UserUrlTime userUrlTime : userUrlTimeList) {
                        //System.out.println(userUrlTime);
                        writer.write(userUrlTime.getUserUrl() + ", " + userUrlTime.getTime() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("обработка файла " + currentFile.toString() + " закончилась");
    }

    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Path inputPath = propertiesLoader.getInputPath();
        Path outputPath = propertiesLoader.getOutputPath();
        FileProcessingThread testThread = new FileProcessingThread(inputPath, outputPath);
        testThread.run();

    }
}
