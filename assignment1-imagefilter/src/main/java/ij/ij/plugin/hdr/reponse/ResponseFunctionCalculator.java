package ij.plugin.hdr.reponse;

import ij.plugin.hdr.Exceptions.TypeNotSupportedException;
import ij.plugin.hdr.reponse.weight.WeightFunction;


/**
 *
 * @author Alexander Heidrich
 */
public interface ResponseFunctionCalculator {

    public double[] calcResponse(int channel, int lambda) throws TypeNotSupportedException;

    public WeightFunction getW();

    public ResponseFunctionCalculatorSettings getResponseFunctionCalculatorSettings();

    public String getAlgorithm();

    public String getAlgorithmReference();
}