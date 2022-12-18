package com.epam.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class MyFileReader {
    private String fileName;

    public MyFileReader(String name) {
        fileName = name;
    }

    public void readAll() {
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
            reader.lines().forEach(System.out::println);

        } catch (FileNotFoundException e) {
            System.out.println("Error in readALl");
        }
    }
}
