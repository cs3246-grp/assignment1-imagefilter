package ij.plugin.hdr.reponse.weight;

/**
 *
 * @author Alexander Heidrich
 */
public class SimpleWeightFunction implements WeightFunction{

    private int Zmin;
    private int Zmax;

    public SimpleWeightFunction(int Zmin, int Zmax) {
        this.Zmin = Zmin;
        this.Zmax = Zmax;
    }

    public double w(int z) {
        if (z <= 0.5 * (Zmin + Zmax)) {
            return z - Zmin;
        } else {
            return Zmax - z;
        }
    }

}