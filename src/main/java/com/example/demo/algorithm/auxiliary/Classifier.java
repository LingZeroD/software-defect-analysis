/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.algorithm.auxiliary;

import java.io.Serializable;

/**
 *
 * @author daq
 */
public abstract class Classifier implements Cloneable, Serializable {

    public abstract void train(boolean[] isCategory, int classifier, double[][] features, double[] labels);

    public abstract double predict(double[] features);
}
