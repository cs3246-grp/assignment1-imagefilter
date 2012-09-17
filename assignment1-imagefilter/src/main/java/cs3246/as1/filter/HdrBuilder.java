package cs3246.as1.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import ij.ImagePlus;
import ij.ImageStack;
import ij.plugin.hdr.calibration.ZMatrix.RandomZMatrixBuilder;
import ij.plugin.hdr.calibration.ZMatrix.ZMatrixBuilder;
import ij.plugin.hdr.radiance.DebevecRadiance;
import ij.plugin.hdr.reponse.ResponseFunction;
import ij.plugin.hdr.reponse.ResponseFunctionCalculator;
import ij.plugin.hdr.reponse.ResponseFunctionCalculatorSettings;
import ij.plugin.hdr.reponse.debevec.DebevecCalculator;

public class HdrBuilder extends AbstractBufferedImageOp {
	private static final String UPLOAD_PATH = "/Users/popol/git/assignment1-imagefilter/assignment1-imagefilter/src/main/webapp/WEB-INF/images/";
	private ImageStack stack;
	private ImagePlus imp;
	private ResponseFunctionCalculatorSettings settings;
	private int[][][] matrixZ;
	private ResponseFunction responseFunction;
	private BufferedImage dest;

	public HdrBuilder() {
	}

	public HdrBuilder(List<String> namelist) {
		try {
			Metadata metadata;
			File fin = new File(UPLOAD_PATH + namelist.get(0));
			BufferedImage image = ImageIO.read(fin);
			dest = createCompatibleDestImage(image, null);
			stack = new ImageStack(image.getWidth(), image.getHeight());
			int numOfPixels = image.getWidth() * image.getHeight();
			int[] pixels = new int[numOfPixels];
			double[] expTime = new double[namelist.size()];
			int count = 0;
			for (String name : namelist) {
				fin = new File(UPLOAD_PATH + name);
				image = ImageIO.read(fin);
				metadata = ImageMetadataReader.readMetadata(fin);
				expTime[count] = metadata.getDirectory(
						ExifSubIFDDirectory.class).getDouble(
						ExifSubIFDDirectory.TAG_EXPOSURE_TIME);
				getRGB(image, 0, 0, image.getWidth(), image.getHeight(), pixels);
				stack.addSlice(new Integer(count).toString(), pixels);
				count++;
			}
			imp = new ImagePlus("hdr", stack);
			ZMatrixBuilder zbuilder = new RandomZMatrixBuilder(imp,
					numOfPixels, namelist.size());
			matrixZ = zbuilder.getZ();
			settings = new ResponseFunctionCalculatorSettings();
			settings.setZmax(getZmax(matrixZ));
			settings.setZmin(getZmin(matrixZ));
			settings.setNoOfPixelsN(numOfPixels);
			settings.setNoOfImages(namelist.size());
			settings.setExpTimes(expTime);
			ResponseFunctionCalculator debevec = new DebevecCalculator(matrixZ,
					settings);
			double[][] functionG = new double[3][settings.getZmax()
					- settings.getZmin() + 1];
			for (int i = 0; i < 3; i++) {
				functionG[i] = debevec.calcResponse(i, 1);
			}
			responseFunction = new ResponseFunction(functionG, 0,
					debevec.getW());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		try {
			DebevecRadiance debevec = new DebevecRadiance(imp,
					responseFunction, settings.getExpTimes());
			double[][] outpixels = new double[3][settings.getNoOfPixelsN()];
			for (int i = 0; i < 3; i++) {
				outpixels[i] = debevec.calcRadiance(i);
			}
			int [] pixels = new int[settings.getNoOfPixelsN()];
			for (int i=0;i<settings.getNoOfPixelsN();i++) {
				int ir = (int) (outpixels[0][i]+0.5);
				int ig = (int) (outpixels[1][i]+0.5);
				int ib = (int) (outpixels[2][i]+0.5);
				
				pixels[i] = (ir << 16) | (ig << 8) | ib;
			}
			setRGB(dest, 0, 0, settings.getWidth(), settings.getHeight(), pixels);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private int getZmax(int[][][] m) {
		int max = -1;
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				for (int k = 0; k < m[i][j].length; k++)
					max = m[i][j][k] > max ? m[i][j][k] : max;
		return max;
	}

	private int getZmin(int[][][] m) {
		int min = 999999999;
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				for (int k = 0; k < m[i][j].length; k++)
					min = m[i][j][k] < min ? m[i][j][k] : min;
		return min;
	}

}
