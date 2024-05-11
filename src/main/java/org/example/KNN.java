package org.example;

public class KNN {

    private double[][] data;

    public KNN(double[][] data) {
        this.data = data;
    }

    public void fillMissingValues() {
        for (int i = 0; i < data.length; i++) {
            double[] instance = data[i];
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

        for (int i = 0; i < data.length; i++) {
            if (i != rowIndex) {
                double[] neighbor = data[i];
                double distance = euclideanDistance(data[rowIndex], neighbor);

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
}
