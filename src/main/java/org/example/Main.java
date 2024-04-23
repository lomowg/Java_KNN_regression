package org.example;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                String[] parts = line.split(",");
                double[] values = new double[parts.length - 5];
                for (int i = 6; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        values[i - 6] = Double.parseDouble(parts[i]);
                    }
                }

                double[] normalizedValues = normalize(values);

                writer.write(parts[0]);
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
                String[] parts = line.split(",");
                double[] values = new double[parts.length - 5];
                for (int i = 6; i < parts.length; i++) {
                    if (!parts[i].isEmpty()) {
                        values[i - 6] = Double.parseDouble(parts[i]);
                    }
                }

                double[] denormalizedValues = denormalize(values);

                writer.write(parts[0]);
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
        Mean mean = new Mean();
        StandardDeviation stdDev = new StandardDeviation();

        double meanValue = mean.evaluate(data);
        double stdDevValue = stdDev.evaluate(data);

        double[] normalizedData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizedData[i] = (data[i] - meanValue) / stdDevValue;
        }

        return normalizedData;
    }

    public static double[] denormalize(double[] normalizedData) {
        Mean mean = new Mean();
        StandardDeviation stdDev = new StandardDeviation();

        double meanValue = mean.evaluate(normalizedData);
        double stdDevValue = stdDev.evaluate(normalizedData);

        double[] denormalizedData = new double[normalizedData.length];
        for (int i = 0; i < normalizedData.length; i++) {
            denormalizedData[i] = (normalizedData[i] * stdDevValue) + meanValue;
        }

        return denormalizedData;
    }
}
