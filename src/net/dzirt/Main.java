package net.dzirt;

import java.io.File;
import java.nio.file.Path;

public class Main {
    private static String propertiesFilePath = "properties.ini"; //ini file with input and output folder paths

    public static void main(String[] args)  {
        PropertiesLoader propertiesLoader = new PropertiesLoader(propertiesFilePath); //Load properties from .ini file
        Path inputPath = propertiesLoader.getInputPath();
        Path outputPath = propertiesLoader.getOutputPath();

        File inputFolder = new File(String.valueOf(inputPath));
        if(inputFolder.exists()) {
            File outputFolder = new File(String.valueOf(outputPath));
            if (!outputFolder.exists()) {
                outputFolder.mkdir(); //creating output folder
            }
            FileThreadPool fileThreadPool = new FileThreadPool(inputPath, outputPath); //Create new thread pool using input and output paths
            System.out.println("Processing...");
            fileThreadPool.startProcessing();   //And then start it
            fileThreadPool.finishProcessing();
        } else {
            System.out.println("Input folder not found!");
        }
    }
}
