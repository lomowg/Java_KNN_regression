package org.example;


public class Main {
    public static void main(String[] args) throws Exception {
        String csvFilePath = "Data_2.csv";

        ReadData rd = new ReadData();
        WriteData wd = new WriteData();
        Preparing prep = new Preparing();
        KNN KNNRegression = new KNN();

        rd.load(csvFilePath);

        double[][] targets = rd.table_target;

        double[] mean_val = new double[targets.length];
        double[] std_val = new double[targets.length];

        for (int col = 0; col < targets.length; col++) {
            targets[col] = prep.normalize(targets[col]);
            mean_val[col] = prep.meanValue;
            std_val[col] = prep.stdDevValue;
        }

        double[][] knn_targets = KNNRegression.train(targets);

        for (int col = 0; col < knn_targets.length; col++) {
            knn_targets[col] = prep.denormalize(knn_targets[col], mean_val[col], std_val[col]);
        }

        wd.write("KNN_test.csv", rd.header, rd.table, knn_targets);

    }
}
