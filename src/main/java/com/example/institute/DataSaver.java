package com.example.institute;

import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {
    public static void saveData(String filename, String[] data){
        try {
            FileWriter fileWriter = new FileWriter(filename);
            for (String s : data){
                fileWriter.write(s);
                fileWriter.write("\n");
            }
            fileWriter.close();
            System.out.println("Data saved to " + filename);
        } catch (IOException e){
            System.out.println("Error saving data to " + filename + ": " + e.getMessage());
        }
    }
}
