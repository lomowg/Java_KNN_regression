package org.example;

public class Preparing {

    static double meanValue;
    static double stdDevValue;

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

    public static double[] normalize(double[] data) {

        meanValue = mean(data);
        stdDevValue = stdDev(data);

        double[] normalizedData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {
                normalizedData[i] = (data[i] - meanValue) / stdDevValue;
            } else {
                normalizedData[i] = Double.NaN;
            }
        }

        return normalizedData;
    }

    public static double[] denormalize(double[] normalizedData, double meanVal, double stdDevVal) {

        double[] denormalizedData = new double[normalizedData.length];

        for (int i = 0; i < normalizedData.length; i++) {
            if (!Double.isNaN(normalizedData[i])) {
                denormalizedData[i] = (normalizedData[i] * stdDevVal) + meanVal;
            } else {
                denormalizedData[i] = Double.NaN;
            }
        }
        return denormalizedData;
    }
}
