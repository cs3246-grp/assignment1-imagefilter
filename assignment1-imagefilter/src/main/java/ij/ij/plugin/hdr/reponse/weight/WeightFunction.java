package ij.plugin.hdr.reponse.weight;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public interface WeightFunction extends Serializable {
    public double w(int value);
}