package com.example.demo.algorithm.predict;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daq
 */
public class PredictDataSet {

    private double[][] features;
    private int numAttributes;
    private int numInstnaces;

    public PredictDataSet(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + path));
            String[] attInfo = reader.readLine().split(","); // attributes info
            numAttributes = attInfo.length;

            numInstnaces = 1;
            while (reader.readLine() != null) {
                numInstnaces++;
            }

            features = new double[numInstnaces][numAttributes];
            System.out.println("reading " + numInstnaces + " exmaples with " + numAttributes + " attributes");

            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + path));
            String line;
            int ind = 0;
            while ((line = reader.readLine()) != null) {
                String[] atts = line.split(",");
                for (int i = 0; i < atts.length ; i++) {
                    features[ind][i] = Double.parseDouble(atts[i]);
                }
                ind++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PredictDataSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PredictDataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double[][] getFeatures() {
        return features;
    }

    public int getNumAttributes() {
        return numAttributes;
    }

    public int getNumInstnaces() {
        return numInstnaces;
    }
}
