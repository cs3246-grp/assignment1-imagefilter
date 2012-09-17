package ij.plugin.hdr.radiance;

import ij.ImagePlus;
import ij.plugin.hdr.Exceptions.TypeNotSupportedException;
import ij.plugin.hdr.helper.ImageJTools;
import ij.plugin.hdr.reponse.ResponseFunction;

/**
 *
 * @author Alexander Heidrich <alexander.heidrich@hki-jena.de>
 */
public class DebevecRadiance implements RadianceMapConstructor {

    private ImagePlus imp;
    private ResponseFunction r;
    private double[] expTimes;

    public DebevecRadiance(ImagePlus imp, ResponseFunction r, double[] expTimes) {
        this.imp = imp;
        this.r = r;
        this.expTimes = expTimes;
    }

    public double[] calcRadiance(int channel) throws TypeNotSupportedException {
        int type = imp.getType();
        int length = imp.getWidth() * imp.getHeight();
        double out[] = new double[length];
        for (int j = 0; j < length; j++) {                        // for all pixels
            double w = 0.0;
            double wgt = 0.0;
            for (int k = 1; k <= imp.getStackSize(); k++) {            // for all images
                Object pixels = imp.getImageStack().getPixels(k);
                // get pixel value at position j and the current channel
                int value = ImageJTools.getPixelValue(pixels, j, type, channel);
                w += r.getW().w(value);
                wgt += r.getW().w(value) * ((r.getG()[channel][value]) - Math.log(expTimes[k - 1]));
                if (j % 123456 == 0) {
                    System.out.println("Value:" + value);
                    System.out.println("w_: " + r.getW().w(value));
                    System.out.println("exp:" + expTimes[k - 1]);
                    System.out.println("g:" + r.getG()[channel][value]);
                    System.out.println("w_sum:" + w);
                    System.out.println("wgt:" + wgt);
                }
                out[j] = wgt / w;
            }
        }
        return out;
    }
}