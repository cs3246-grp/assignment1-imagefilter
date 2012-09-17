package cs3246.as1.filter;

import java.awt.image.BufferedImage;

public class LomoFilter extends AbstractBufferedImageOp {
	float scaleValue;

	public LomoFilter() {
		scaleValue = 95 * 1.0F / 127;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
		int height = src.getHeight();

		double radius = (double) (width / 2) * 95 / 100;
		double centerX = width / 2f;
		double centerY = height / 2f;

		dest = createCompatibleDestImage(src, null);
		int[] inPixels = new int[width * height];

		getRGB(src, 0, 0, width, height, inPixels);

		int currentPos;
		double pixelsFallOff = 1.5;

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				double dis = Math.sqrt(Math.pow((centerX - col), 2)
						+ Math.pow(centerY - row, 2));
				currentPos = row * width + col;
				float r = 0, g = 0, b = 0;

				if (dis > radius) {

					int rgb = inPixels[currentPos];
					r = ((rgb >> 16) & 0xff);
					g = ((rgb >> 8) & 0xff);
					b = (rgb & 0xff);

					double scaler = scaleFunction(radius, dis, pixelsFallOff);
					scaler = Math.abs(scaler);
					
					int ir = (int) (r - scaler);
					int ig = (int) (g - scaler);
					int ib = (int) (b - scaler);

					ir = Math.min(255, Math.max(0, ir));
					ig = Math.min(255, Math.max(0, ig));
					ib = Math.min(255, Math.max(0, ib));

					inPixels[currentPos] = (ir << 16) | (ig << 8) | ib;
				}
			}
		}
		setRGB(dest, 0, 0, width, height, inPixels);
		return dest;
	}

	private double scaleFunction(double radius, double dis, double pixelsFallOff) {
		return 1 - Math.pow((double) ((dis - radius) / pixelsFallOff), 2);
	}

}
