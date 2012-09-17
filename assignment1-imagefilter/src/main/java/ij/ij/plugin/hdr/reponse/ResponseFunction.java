package ij.plugin.hdr.reponse;

import ij.plugin.hdr.reponse.weight.WeightFunction;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class ResponseFunction implements Serializable {
    private double[][] g;
    private int type;
    private WeightFunction w;

    public ResponseFunction(double[][] g, int type, WeightFunction w) {
        this.g = g;
        this.type = type;
        this.w = w;
    }

    /**
     * @return the g
     */
    public double[][] getG() {
        return g;
    }

    public int getType() {
        return type;
    }

    /**
     * @return the w
     */
    public WeightFunction getW() {
        return w;
    }

}