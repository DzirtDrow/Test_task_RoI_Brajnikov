package net.dzirt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    private File inputFile;
    private List<LineOfFile> linesList = new ArrayList<>();

    public CSVReader(File inputFile) {
        this.inputFile = inputFile;
    }

    public List readLinesOfFile(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile)); //Creating reader of CSV file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line; //Read lines of CSV files
        Scanner scanner;
        int index = 0;
        try {
            if((reader.readLine()) != null) { // reading Header of csv file ( it is not used in the future)
                while ((line = reader.readLine()) != null) {
                    LineOfFile lineOfFile = new LineOfFile(); // Read one line
                    scanner = new Scanner(line);    //And then disassemling this line to 4 rows
                    scanner.useDelimiter(",");      //by "," delimiter
                    while (scanner.hasNext()) {
                        String data = scanner.next();
                        if (index == 0)
                            lineOfFile.setTimeStamp(Long.parseLong(data));  //Getting timestamp
                        else if (index == 1)
                            lineOfFile.setId(data);                         //Getting user id
                        else if (index == 2)
                            lineOfFile.setUrl(data);                        //Getting url
                        else if (index == 3)
                            lineOfFile.setTime(Long.parseLong(data));       //Getting time
                        else
                            System.out.println("incorrect data " + data);
                        index++;
                    }
                    index = 0;
                    linesList.add(lineOfFile);                              //Adding line to List of lines
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linesList;
    }

}
