package net.dzirt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesLoader {

    private Properties props = new Properties();

    private Path inputPath;
    private Path outputPath;

    public PropertiesLoader(String propertiesFilePath) {
        try {
            props.load(new FileInputStream(new File(propertiesFilePath)));

            inputPath = Paths.get(props.getProperty("INPUT_FOLDER"));
            outputPath = Paths.get(props.getProperty("OUTPUT_FOLDER"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Path getInputPath() {
        return inputPath;
    }

    public Path getOutputPath() {
        return outputPath;
    }
}
