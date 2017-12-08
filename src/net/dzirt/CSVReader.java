package net.dzirt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private Path inputFilePath;
    private List<LineOfFile> linesList = new ArrayList<>();

    public CSVReader(Path inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void setInputFilePath(Path inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public List readLinesOfFile(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(String.valueOf(inputFilePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // read lines
        String line = null;
        Scanner scanner = null;
        int index = 0;
        try {
            while ((line = reader.readLine()) != null) {
                LineOfFile lineOfFile = new LineOfFile();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0)
                        lineOfFile.setTimeStamp(Long.parseLong(data));
                    else if (index == 1)
                        lineOfFile.setId(data);
                    else if (index == 2)
                        lineOfFile.setUrl(data);
                    else if (index == 3)
                        lineOfFile.setTime(Long.parseLong(data));
                    else
                        System.out.println("Некорректные данные: " + data);
                    index++;
                }
                index = 0;
                linesList.add(lineOfFile);
            }
            reader.close();

            //System.out.println(linesList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesList;
    }


    public List<LineOfFile> getLinesList() {
        return linesList;
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader(Paths.get("D:\\Work\\Andrey\\Java\\IdeaProjects\\Test_task_RoI_Brajnikov\\Input\\file1.csv"));
        List<LineOfFile> list = csvReader.readLinesOfFile();
        System.out.println(list);
    }

}
