package net.dzirt;

import java.nio.file.Path;

public class Main {
    private static String propertiesFilePath = "properties4.ini"; //ini file with input and output folder paths

    public static void main(String[] args)  {
        PropertiesLoader propertiesLoader = new PropertiesLoader(propertiesFilePath); //FLoad properties from .ini file
        Path inputPath = propertiesLoader.getInputPath();
        Path outputPath = propertiesLoader.getOutputPath();

        FileThreadPool fileThreadPool = new FileThreadPool(inputPath, outputPath); //Create new thread pool using input and output paths
        fileThreadPool.startProcessing();   //And then start it

    }
}
