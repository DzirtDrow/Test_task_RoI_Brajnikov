package net.dzirt;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.*;


public class FileThreadPool implements Runnable{
    private Path inputPath;
    private Path outputPath;

    public FileThreadPool(Path inputPath, Path outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void startProcessing() {
        File[] fList;               //Creating of list files in input folder
        File f = new File(String.valueOf(inputPath));
        fList = f.listFiles();

        if (fList.length>0){
            ExecutorService threadPool = Executors.newFixedThreadPool(10); //Creating pool of 10 threads
            for (int i = 0; i<fList.length; i++) {
                threadPool.submit(new FileProcessingThread(outputPath, fList[i])); //And then load all files in queue (if files more than 10, they are waiting for the release threads
            }
            threadPool.shutdown(); //End of using threadpool
        } else {
            System.out.println("Input folder contains no files");
        }

    }

    @Override
    public void run() {
        try {
            WatchService watchService = inputPath.getFileSystem().newWatchService();
            inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

            // loop forever to watch directory
            while (true) {
                WatchKey watchKey;
                watchKey = watchService.take(); // this call is blocking until events are present

                // poll for file system events on the WatchKey
                for (final WatchEvent<?> event : watchKey.pollEvents()) {

                }

                // if the watched directed gets deleted, get out of run method
                if (!watchKey.reset()) {
                    System.out.println("No longer valid");
                    watchKey.cancel();
                    watchService.close();
                    break;
                }
            }

        } catch (InterruptedException ex) {
            System.out.println("interrupted. Goodbye");
            return;
        } catch (IOException ex) {
            ex.printStackTrace();  // don't do this in production code. Use a loggin framework
            return;
        }
    }
}
