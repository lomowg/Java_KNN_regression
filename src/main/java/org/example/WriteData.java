package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {

    public void write(String csvFilePath, String Header, String[][] table, double[][] table_target) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath));
        writer.write(Header + "\n");

        for (int row = 0; row < table[0].length; row++) {

            writer.write(table[0][row]);
            for (int col = 1; col < table.length; col++) {
                writer.write("," + table[col][row]);
            }

            for (double[] doubles : table_target) {
                writer.write("," + doubles[row]);
            }

            writer.write("\n");
        }

        writer.close();
    }
}