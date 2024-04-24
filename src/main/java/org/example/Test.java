package org.example;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        String csvFilePath = "Data_2.csv";

        ReadData ld = new ReadData();
        WriteData wd = new WriteData();

        ld.load(csvFilePath);

        try {
            wd.write("test_1.csv", ld.header, ld.table, ld.table_target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
