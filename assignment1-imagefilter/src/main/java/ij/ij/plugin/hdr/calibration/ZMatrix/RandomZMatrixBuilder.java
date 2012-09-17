package ij.plugin.hdr.calibration.ZMatrix;

import ij.ImagePlus;
import ij.plugin.hdr.Exceptions.TypeNotSupportedException;
import ij.plugin.hdr.helper.ImageJTools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander Heidrich
 */
public class RandomZMatrixBuilder implements ZMatrixBuilder {

    public static final Logger log = Logger.getLogger(RandomZMatrixBuilder.class.getName());
    private ImagePlus imp;
    private int noOfImagesP;
    private int noOfPixelsN;
    private int noOfChannels;
    private int imgWidth;
    private int imgHeight;
    private Random rnd = new Random();

    public RandomZMatrixBuilder(ImagePlus imp, int noOfPixelsN, int noOfImagesP) {
        this.imp = imp;
        this.noOfPixelsN = noOfPixelsN;
        this.noOfChannels = imp.getChannelProcessor().getNChannels();
        this.noOfImagesP = noOfImagesP;
        this.imgHeight = imp.getHeight();
        this.imgWidth = imp.getWidth();
    }

    public int[][][] getZ() throws TypeNotSupportedException {
        // collect random image positions
        HashSet<Integer> pixtemp = new HashSet<Integer>();
        log.log(Level.FINE, "Selecting random pixels - Start");
        while (pixtemp.size() < (noOfPixelsN)) {
            pixtemp.add(getNextRandom());
        }
        log.log(Level.FINE, "Selecting random pixels - End");
        // convert to ArrayList for easier access to the elements
        ArrayList<Integer> pixels = new ArrayList<Integer>(pixtemp);
        // create new Z matrix
        int[][][] Z = new int[noOfChannels][noOfPixelsN][noOfImagesP];
        // fill matrix
        log.log(Level.FINE, "Filling Z-Matrix - Start");
        for (int i = 0; i < Z.length; i++) {                 // for all channels
            for (int j = 0; j < Z[i].length; j++) {          // for all pixels
                for (int k = 0; k < Z[i][j].length; k++) {   // for all images
                    // ImageJ counts images starting at 1 so 1 has to be added to k
                    Z[i][j][k] = ImageJTools.getPixelValue(imp.getImageStack().getPixels(k + 1), pixels.get(j), imp.getType(), i);
                }
            }
        }
        log.log(Level.FINE, "Filling Z-Matrix - End");
        return Z;
    }

    private int getNextRandom() {
        return rnd.nextInt(imgWidth * imgHeight);
    }
}