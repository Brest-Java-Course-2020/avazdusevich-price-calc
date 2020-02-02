package com.epam.brest.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
    private static String address;

    public MyFileWriter(String filePath) {
        address = filePath;
    }

    public void writeStrToFile(String str) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(address));) {
            bufferedWriter.write(str + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
