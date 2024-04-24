package org.example;

import java.io.*;

public class ReadData {
    static public String header;
    static double[] temperature = new double[1455324];
    static double[] pressure = new double[1455324];
    static double[] wind = new double[1455324];
    static double[] snow = new double[1455324];
    static double[] precipitation = new double[1455324];

    static public double[][] table_target = new double[5][1455324];
    static public String[][] table = new String[6][1455324];

    public static void main(String[] args) {


    }
    public static void load(String csvFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));

            header = reader.readLine();

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = splitStringIntoValues(line);
                for (int col = 0; col < parts.length; col++) {
                    if (col >= 6) {
                        if (!parts[col].isEmpty()) {
                            table_target[col-6][row] = Double.parseDouble(parts[col]);
                        } else {
                            table_target[col-6][row] = Double.NaN;
                        }
                    }
                    else {
                        table[col][row] = parts[col];
                    }
                }
                row++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] splitStringIntoValues(String input) {
        String[] parts = input.split(",", -1);

        if (parts.length < 11) {
            int emptyCount = 11 - parts.length;
            for (int i = 0; i < emptyCount; i++) {
                input += ",";
            }
            parts = input.split(",", -1);
        }

        return parts;
    }
}
