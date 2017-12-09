package net.dzirt;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.*;


public class FileThreadPool {
    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Path inputPath = propertiesLoader.getInputPath();
        Path outputPath = propertiesLoader.getOutputPath();

        File[] fList;
        File F = new File(String.valueOf(inputPath));

        fList = F.listFiles();

        for(int i=0; i<fList.length; i++)
        {
            if(fList[i].isFile())
                System.out.println(String.valueOf(i) + " - " + fList[i].getName());
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i<fList.length; i++) {
            threadPool.submit(new FileProcessingThread(outputPath, fList[i]));
        }
        threadPool.shutdown();


        //FileProcessingThread testThread = new FileProcessingThread(inputPath, outputPath);
        //testThread.run();
    }


}
