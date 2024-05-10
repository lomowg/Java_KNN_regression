package org.example;

import java.util.ArrayList;
import java.util.List;

public class KNN {

    private List<double[]> data;

    public KNN(List<double[]> data) {
        this.data = data;
    }

    public void fillMissingValues() {
        for (int i = 0; i < data.size(); i++) {
            double[] instance = data.get(i);
            for (int j = 0; j < instance.length; j++) {
                if (Double.isNaN(instance[j])) {
                    double nearestNeighborValue = findNearestNeighborValue(i, j);
                    instance[j] = nearestNeighborValue;
                }
            }
        }
    }

    private double findNearestNeighborValue(int rowIndex, int columnIndex) {
        double minDistance = Double.MAX_VALUE;
        double nearestNeighborValue = 0.0;

        for (int i = 0; i < data.size(); i++) {
            if (i != rowIndex) {
                double[] neighbor = data.get(i);
                double distance = euclideanDistance(data.get(rowIndex), neighbor);

                if (distance < minDistance && !Double.isNaN(neighbor[columnIndex])) {
                    minDistance = distance;
                    nearestNeighborValue = neighbor[columnIndex];
                }
            }
        }

        return nearestNeighborValue;
    }

    private double euclideanDistance(double[] instance1, double[] instance2) {
        double distance = 0.0;
        for (int i = 0; i < instance1.length; i++) {
            if (!Double.isNaN(instance1[i]) && !Double.isNaN(instance2[i])) {
                distance += Math.pow(instance1[i] - instance2[i], 2);
            }
        }
        return Math.sqrt(distance);
    }

    public static void main(String[] args) {
        List<double[]> data = new ArrayList<>();
        data.add(new double[]{25.0, 1013.2, 10.0, 5.0, 20.0});
        data.add(new double[]{20.0, Double.NaN, 8.0, 3.0, 15.0});
        data.add(new double[]{22.0, 1015.5, 12.0, Double.NaN, 18.0});
        data.add(new double[]{12.0, Double.NaN, 10.0, Double.NaN, Double.NaN});
        data.add(new double[]{22.0, 1013.2, Double.NaN, Double.NaN, Double.NaN});

        KNN imputer = new KNN(data);
        imputer.fillMissingValues();

        for (double[] instance : data) {
            for (double value : instance) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
