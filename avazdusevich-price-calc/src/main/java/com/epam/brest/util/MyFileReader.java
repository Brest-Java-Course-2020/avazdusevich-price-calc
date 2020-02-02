package com.epam.brest.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {
    private static String address;

    public MyFileReader(String filePath) {
        address = filePath;
    }

    public static ArrayList<String> readFromFile() {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(address))) {
            String line;
            while((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (ArrayList<String>) list;
    }


}
