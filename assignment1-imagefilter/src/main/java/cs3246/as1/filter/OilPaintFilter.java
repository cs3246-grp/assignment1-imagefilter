package cs3246.as1.filter;

import java.awt.image.BufferedImage;
import java.util.Random;

public class OilPaintFilter extends AbstractBufferedImageOp {
	int radius;

	public OilPaintFilter(int radius) {
		this.radius = radius;
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {

		int width = src.getWidth();
		int height = src.getHeight();
		int stride = width;
		
		dest = createCompatibleDestImage(src, null);
		int[] rgbArray = src.getRGB(0, 0, width, height, null, 0, stride);

		Random rand = new Random();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int x = col + rand.nextInt(radius);
				int y = row + rand.nextInt(radius);
				while (x < 0 || x >= width || y < 0 || y >= height) {
					x = col + rand.nextInt(radius);
					y = row + rand.nextInt(radius);
				}
				dest.setRGB(col, row, rgbArray[y * stride + x]);
			}
		}


		return dest;
	}

}
