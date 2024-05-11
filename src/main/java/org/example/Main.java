package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String csvFilePath = "Data_2_2.csv";

        ReadData rd = new ReadData();
        WriteData wd = new WriteData();
        Preparing prep = new Preparing();

        rd.load(csvFilePath);


        double[][] targets = rd.table_target;

        double[] mean_val = new double[targets.length];
        double[] std_val = new double[targets.length];

        for (int col = 0; col < targets.length; col++) {
            targets[col] = prep.normalize(targets[col]);
            mean_val[col] = prep.meanValue;
            std_val[col] = prep.stdDevValue;
        }

        targets = transpose(targets);

        KNN imputer = new KNN(targets);
        imputer.fillMissingValues();

        double[][] knn_targets = transpose(targets);

        for (int col = 0; col < knn_targets.length; col++) {
            knn_targets[col] = prep.denormalize(knn_targets[col], mean_val[col], std_val[col]);
        }

        wd.write("KNN_test.csv", rd.header, rd.table, knn_targets);

    }

    public static double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] transposed = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }

        return transposed;
    }
}
