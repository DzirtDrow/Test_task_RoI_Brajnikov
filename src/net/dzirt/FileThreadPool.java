package net.dzirt;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.*;


public class FileThreadPool {
    private Path inputPath;
    private Path outputPath;

    public FileThreadPool(Path inputPath, Path outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void startProcessing() {
        File[] fList;               //Creating of list files in input folder
        File F = new File(String.valueOf(inputPath));
        fList = F.listFiles();

        ExecutorService threadPool = Executors.newFixedThreadPool(10); //Creating pool of 10 threads
        for (int i = 0; i<fList.length; i++) {
            threadPool.submit(new FileProcessingThread(outputPath, fList[i])); //And then load all files in queue (if files more than 10, they are waiting for the release threads
        }
        threadPool.shutdown(); //End of using threadpool
    }
}
