package org.example;

import java.io.*;
public class Main {

    public static void main(String[] args) {
        String csvFilePath = "Data_2.csv";

        normalizeData(csvFilePath);

        denormalizeData(csvFilePath + "_normalized.csv");
    }

    public static void normalizeData(String csvFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath + "_normalized.csv"));

            String header = reader.readLine();
            writer.write(header + "\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = splitStringIntoValues(line);
                double[] values = new double[parts.length - 6]; // Учитываем только нужные столбцы
                for (int i = 6; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        values[i - 6] = Double.parseDouble(parts[i]);
                    } else {
                        // Заменяем пропуски на NaN
                        values[i - 6] = Double.NaN;
                    }
                }

                double[] normalizedValues = normalize(values);

                writer.write(parts[0]);
                for (int i = 1; i < 6; i++){
                    writer.write("," + parts[i]);
                }

                for (double val : normalizedValues) {
                    writer.write("," + val);
                }
                writer.write("\n");
            }

            reader.close();
            writer.close();

            System.out.println("Normalization completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void denormalizeData(String normalizedCsvFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(normalizedCsvFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(normalizedCsvFilePath.replace("_normalized.csv", "_denormalized.csv")));

            String header = reader.readLine();
            writer.write(header + "\n");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = splitStringIntoValues(line);
                double[] values = new double[parts.length - 6]; // Учитываем только нужные столбцы
                for (int i = 6; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        values[i - 6] = Double.parseDouble(parts[i]);
                    }
                    else {
                        // Заменяем пропуски на NaN
                        values[i - 6] = Double.NaN;
                    }
                }

                double[] denormalizedValues = denormalize(values);

                writer.write(parts[0]);
                for (int i = 1; i < 6; i++){
                    writer.write("," + parts[i]);
                }

                for (double val : denormalizedValues) {
                    writer.write("," + val);
                }
                writer.write("\n");
            }

            reader.close();
            writer.close();

            System.out.println("Denormalization completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[] normalize(double[] data) {

        double meanValue = mean(data);
        double stdDevValue = stdDev(data);

        double[] normalizedData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {
                normalizedData[i] = (data[i] - meanValue) / stdDevValue;
            } else {
                // Пропуск не требует нормализации
                normalizedData[i] = Double.NaN;
            }
        }

        return normalizedData;
    }

    public static double[] denormalize(double[] normalizedData) {

        double meanValue = mean(normalizedData);
        double stdDevValue = stdDev(normalizedData);

        double[] denormalizedData = new double[normalizedData.length];
        for (int i = 0; i < normalizedData.length; i++) {
            if (!Double.isNaN(normalizedData[i])) {
                denormalizedData[i] = (normalizedData[i] * stdDevValue) + meanValue;
            } else {
                // Пропуск не требует денормализации
                denormalizedData[i] = Double.NaN;
            }
        }

        return denormalizedData;
    }

    public static String[] splitStringIntoValues(String input) {
        String[] parts = input.split(",", -1); // -1 argument to include trailing empty strings

        // Ensure there are at least 11 parts
        if (parts.length < 11) {
            // Add empty values if needed
            int emptyCount = 11 - parts.length;
            for (int i = 0; i < emptyCount; i++) {
                input += ",";
            }
            parts = input.split(",", -1);
        }

        return parts;
    }

    public static double mean(double[] data) {
        double sum = 0;
        for (double value : data) {
            if (!Double.isNaN(value)) {
                sum += value;
            }
        }
        return sum / data.length;
    }

    public static double stdDev(double[] data) {
        double mean = mean(data);
        double sumSquaredDiff = 0;
        for (double value : data) {
            if (!Double.isNaN(value)) {
                double diff = value - mean;
                sumSquaredDiff += diff * diff;
            }
        }
        return Math.sqrt(sumSquaredDiff / data.length);
    }
}
