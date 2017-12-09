package net.dzirt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {
    private String propertiesFilePath = "properties4.ini";
    private String inputFilePath;
    private String outputFilePath;
    private Properties props = new Properties();

    private Path inputPath;
    private Path outputPath;


    public PropertiesLoader() {
        try {
            props.load(new FileInputStream(new File(propertiesFilePath)));

            inputPath = Paths.get(props.getProperty("INPUT_FOLDER"));
            outputPath = Paths.get(props.getProperty("OUTPUT_FOLDER"));

            //inputFilePath = props.getProperty("INPUT_FOLDER");
            //outputFilePath = props.getProperty("OUTPUT_FOLDER");
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public String getInputFilePath(){
        return inputFilePath;
    }
    public String getOutputFilePath(){
        return outputFilePath;
    }


    public static void main(String[] args) {
        PropertiesLoader pl = new PropertiesLoader();
        System.out.println(pl.getInputPath());
        System.out.println(pl.getOutputPath());
        File test = new File(pl.inputFilePath + "\\file1.csv");
        System.out.println(test.exists());




    }

    public Path getInputPath() {
        return inputPath;
    }

    public Path getOutputPath() {
        return outputPath;
    }
}
