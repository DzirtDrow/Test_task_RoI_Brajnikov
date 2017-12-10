package net.dzirt;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.*;


public class FileThreadPool implements Runnable{
    private Path inputPath;
    private Path outputPath;
    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public FileThreadPool(Path inputPath, Path outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public void startProcessing() {
        File[] fList;               //Creating of list files in input folder
        File f = new File(String.valueOf(inputPath));
        if(f.exists()) {
            fList = f.listFiles();
            if (fList.length > 0) {
                //Creating pool of 10 threads
                for (File aFList : fList) {
                    if (getFileExtension(aFList).equals("csv")) {
                        threadPool.submit(new FileProcessingThread(outputPath, aFList)); //And then load all files in queue (if files more than 10, they are waiting for the release threads
                    }
                }

            } else {
                System.out.println("Input folder contains no files");
            }
            this.run();
        } else {
            System.out.println("Input folder does not exist");
        }
    }

    public void finishProcessing() {
        threadPool.shutdown(); //End of using threadpool
    }

    @Override
    public void run() {
        try {
            WatchService watchService = inputPath.getFileSystem().newWatchService();
            // loop forever to watch directory
            WatchKey watchKey;
            inputPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                watchKey = watchService.take(); // this call is blocking until events are present

                for (WatchEvent<?> event: watchKey.pollEvents()) {
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Thread.sleep(10); //waiting for file creating in this folder
                    Path filename = ev.context();
                    File file = new File(inputPath + "\\" + String.valueOf(filename));
                    if (file.exists()&&(getFileExtension(file).equals("csv"))) {
                        threadPool.submit(new FileProcessingThread(outputPath, file)); // add new file to threadpool
                    }
                    watchKey.reset();
                }
            }

        } catch (InterruptedException ex) {
            System.out.println("interrupted.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getFileExtension(File file) {
        if(file.isFile()){
            String[] sp = file.getName().split("\\.");
            return sp[sp.length - 1];
        }
        return "";
    }


}
