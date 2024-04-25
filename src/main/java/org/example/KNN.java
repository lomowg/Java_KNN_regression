package org.example;

import weka.core.*;
import weka.filters.*;
import weka.filters.unsupervised.attribute.*;

public class KNN {

    public static double[][] train(double[][] data) throws Exception {
        Instances instances = createInstances(data);
        ReplaceMissingValues filter = new ReplaceMissingValues();

        filter.setInputFormat(instances);
        Instances filledInstances = Filter.useFilter(instances, filter);

        double[][] filledData = getFilledData(filledInstances);

        return filledData;
    }


    private static Instances createInstances(double[][] data) {
        // Создаем атрибуты
        FastVector attributes = new FastVector();
        for (int i = 0; i < data.length; i++) {
            Attribute attribute = new Attribute("Attribute_" + i);
            attributes.addElement(attribute);
        }

        Instances instances = new Instances("Data", attributes, data.length);
        for (int i = 0; i < data[0].length; i++) {
            Instance instance = new DenseInstance(data.length);
            for (int j = 0; j < data[i].length; j++) {
                instance.setValue((Attribute) attributes.elementAt(j), data[j][i]);
            }
            instances.add(instance);
        }

        return instances;
    }

    private static double[][] getFilledData(Instances instances) {
        double[][] filledData = new double[instances.numAttributes()][instances.numInstances()];
        for (int i = 0; i < instances.numAttributes(); i++) {
            for (int j = 0; j < instances.numInstances(); j++) {
                filledData[i][j] = instances.instance(j).value(i);
            }
        }
        return filledData;
    }

}
