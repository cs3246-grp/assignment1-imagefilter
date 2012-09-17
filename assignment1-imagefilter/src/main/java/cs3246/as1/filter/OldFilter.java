package cs3246.as1.filter;

import java.awt.image.BufferedImage;

public class OldFilter extends AbstractBufferedImageOp {

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		int width = src.getWidth();
		int height = src.getHeight();

		dest = createCompatibleDestImage(src, null);
		int[] inPixels = new int[width * height];

		getRGB(src, 0, 0, width, height, inPixels);

		int rgb = 0;
		int r = 0;
		int g = 0;
		int b = 0;
		int ir = 0;
		int ig = 0;
		int ib = 0;

		for (int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				
				rgb = inPixels[width * i + k];
				
				r = ((rgb >> 16) & 0xff);
				g = ((rgb >> 8) & 0xff);
				b = (rgb & 0xff);

				ir = (int) (0.393 * r + 0.769 * g + 0.189 * b);
				ig = (int) (0.349 * r + 0.686 * g + 0.168 * b);
				ib = (int) (0.272 * r + 0.534 * g + 0.131 * b);

				ir = ir > 255 ? 255 : ir;
				ig = ig > 255 ? 255 : ig;
				ib = ib > 255 ? 255 : ib;

				inPixels[width * i + k] = (ir << 16) | (ig << 8) | ib;

			}
		}

		setRGB(dest, 0, 0, width, height, inPixels);
		return dest;
	}

}
