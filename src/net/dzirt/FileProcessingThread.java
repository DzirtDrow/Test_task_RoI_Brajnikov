package net.dzirt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileProcessingThread implements Runnable {
    private Path inputFilePath;
    private Path outputFilePath;


    public FileProcessingThread(Path inputFilePath, Path outputFilePath){
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader(Paths.get("D:\\Work\\Andrey\\Java\\IdeaProjects\\Test_task_RoI_Brajnikov\\Input\\file1.csv"));
        List<LineOfFile> list = csvReader.readLinesOfFile();
        System.out.println(list);
    }
}
