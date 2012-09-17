package ij.plugin.hdr.helper;

import ij.ImagePlus;
import ij.plugin.hdr.Exceptions.TypeNotSupportedException;

/**
 *
 * @author Alex
 */
public class ImageJTools {
   
    private static int[][][] convert16bitGrayToInt(Object[] imageArray, int noOfImagesQ, int imgWidth, int imgHeight) {
        int[][][] converted = new int[1][imgWidth * imgHeight][noOfImagesQ];
        for (int i = 0; i < noOfImagesQ; i++) {
            for (int j = 0; j < imgWidth * imgHeight; j++) {
                converted[0][j][i] = (((short[]) imageArray[i])[j] & 0xffff);
            }
        }
        return converted;
    }

    private static int[][][] convertRGBToInt(Object[] imageArray, int noOfImagesQ, int imgWidth, int imgHeight) {
        int[][][] converted = new int[3][imgWidth * imgHeight][noOfImagesQ];
        for (int i = 0; i < noOfImagesQ; i++) {
            for (int j = 0; j < imgWidth * imgHeight; j++) {
                converted[0][j][i] = (((int[]) imageArray[i])[j] & 0xff0000) >> 16;
                converted[1][j][i] = (((int[]) imageArray[i])[j] & 0x00ff00) >> 8;
                converted[2][j][i] = (((int[]) imageArray[i])[j] & 0x0000ff);
            }
        }
        return converted;
    }

    /**
     * 
     * @param pixels
     * @param position
     * @param type
     * @param channel
     * @return
     * @throws hdr_plugin.Exceptions.TypeNotSupportedException
     */
    public static int getPixelValue(Object pixels, int position, int type, int channel) throws TypeNotSupportedException {
        switch (type) {
            case ImagePlus.GRAY16:
                return ((short[]) pixels)[position] & 0xffff;
            case ImagePlus.COLOR_RGB:
                if (channel == 0) {
                    return (((int[]) pixels)[position] & 0xff0000) >> 16;
                }
                if (channel == 1) {
                    return (((int[]) pixels)[position] & 0x00ff00) >> 8;
                }
                if (channel == 2) {
                    return (((int[]) pixels)[position] & 0x0000ff);
                }
            default:
                throw new TypeNotSupportedException("The image has an unsupported image type!");
        }
    }
    

    /**
     *
     * @param imp
     * @return
     */
    public static int[][][] checkAndConvert(ImagePlus imp) {
        int type = imp.getType();
        if (type == ImagePlus.GRAY16) {
            return convert16bitGrayToInt(imp.getStack().getImageArray(), imp.getStackSize(), imp.getWidth(), imp.getHeight());
        }
        if (type == ImagePlus.COLOR_RGB) {
            return convertRGBToInt(imp.getStack().getImageArray(), imp.getStackSize(), imp.getWidth(), imp.getHeight());
        }
        return null;
    }
}