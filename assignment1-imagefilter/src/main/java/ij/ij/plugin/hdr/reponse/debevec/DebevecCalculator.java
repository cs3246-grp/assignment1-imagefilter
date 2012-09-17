package ij.plugin.hdr.reponse.debevec;

import ij.plugin.hdr.helper.ArrayTools;
import ij.plugin.hdr.reponse.ResponseFunctionCalculator;
import ij.plugin.hdr.reponse.ResponseFunctionCalculatorSettings;
import ij.plugin.hdr.reponse.weight.SimpleWeightFunction;
import ij.plugin.hdr.reponse.weight.WeightFunction;

import java.util.logging.Level;
import java.util.logging.Logger;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.Vector;

/**
 *
 * @author Alexander Heidrich
 */
public class DebevecCalculator implements ResponseFunctionCalculator {

    public static final Logger log = Logger.getLogger(DebevecCalculator.class.getName());
    private int[][][] imgPixelsZ;
    private ResponseFunctionCalculatorSettings settings;
    private WeightFunction w;
   

    public DebevecCalculator(int[][][] imgPixelsZ, ResponseFunctionCalculatorSettings settings) {
        this.imgPixelsZ = imgPixelsZ;
        this.settings = settings;
        w = new SimpleWeightFunction(settings.getZmin(), settings.getZmax());
    }

    public double[] calcResponse(int channel, int lambda) {
        int n = settings.getZmax() - settings.getZmin() + 1;
        int mid = n / 2;
        int k = 0;

        log.log(Level.FINE, "Debevec - Start");

        double[][] a = new double[settings.getNoOfPixelsN() * settings.getNoOfImages() + n - 1][n + settings.getNoOfPixelsN()];
        double[] b = new double[a.length];

        for (int i = 0; i < imgPixelsZ[channel].length; i++) {    // for all pixels
            for (int j = 0; j < settings.getNoOfImages(); j++) { // for all images
                int value = imgPixelsZ[channel][i][j];
                double wij = w.w(value);
                if (wij == 0.) {
                    continue;
                }
                a[k][value] = wij;
                a[k][n + i] = -wij;
                b[k] = wij * Math.log(settings.getExpTimes()[j]);
                k++;
            }
        }

        a[k][mid] = 1.0;
        k++;

        for (int i = 0; i < n - 2; i++) {
            a[k][i] = lambda * w.w(i + 1);
            a[k][i + 1] = -2.0 * lambda * w.w(i + 1);
            a[k][i + 2] = lambda * w.w(i + 1);
            k++;
        }

        if (k < settings.getNoOfPixelsN() * settings.getNoOfImages() + n - 1) {
            double[][] at = new double[k][n + settings.getNoOfPixelsN()];
            double[] bt = new double[k];
            at = ArrayTools.subarray2D(a, 0, k - 1, 0, n + settings.getNoOfPixelsN() - 1);
            bt = ArrayTools.subarray1D(b, 0, k - 1);
            a = at;
            b = bt;
        }

        log.log(Level.FINE, "Solver - Start");
        
        Matrix A = new DenseMatrix(a);
        Vector B = new DenseVector(b);
        Vector X = new DenseVector(A.numColumns()); 
        A.solve(B, X);
        DenseVector ax = new DenseVector(X);
        double[] result = ax.getData();

        log.log(Level.FINE, "Solver - End");
        log.log(Level.FINE, "Debevec - End");

        // return only the subarray containing the n log exposure values
        return ArrayTools.subarray1D(result, 0, n-1);        
    }

    public ResponseFunctionCalculatorSettings getResponseFunctionCalculatorSettings() {
        return settings;
    }

    public String getAlgorithm() {
        return "Debevec";
    }

    public String getAlgorithmReference() {
        return " ";
    }

    public WeightFunction getW() {
        return w;
    }
}