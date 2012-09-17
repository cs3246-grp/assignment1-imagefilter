package cs3246.as1.filter;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;

public class BlurFilter extends AbstractBufferedImageOp {

	protected Kernel kernel;

	public BlurFilter() {

		float[] blurMatrix = { 1 / 14f, 2 / 14f, 1 / 14f, 2 / 14f, 2 / 14f,
				2 / 14f, 1 / 14f, 2 / 14f, 1 / 14f };

		this.kernel = new Kernel(3, 3, blurMatrix);
	}

	@Override
	public BufferedImage filter(BufferedImage src, BufferedImage dest) {

		int width = src.getWidth();
		int height = src.getHeight();

		if (dest == null)
			dest = createCompatibleDestImage(src, null);

		int[] inPixels = new int[width * height];
		int[] outPixels = new int[width * height];
		getRGB(src, 0, 0, width, height, inPixels);
		convolve(kernel, inPixels, outPixels, width, height);
		setRGB(dest, 0, 0, width, height, outPixels);
		return dest;
	}

	public static void convolve(Kernel kernel, int[] inPixels, int[] outPixels,
			int width, int height) {

		int index = 0;
		float[] matrix = kernel.getKernelData(null);

		int rows = kernel.getHeight();
		int cols = kernel.getWidth();

		int rows2 = (int)(rows / 2);
		int cols2 = (int)(cols / 2);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				
				int pixel = (y * width + x) * 4;
				float r = 0, g = 0, b = 0;

				for (int row = -rows2; row <= rows2; row++) {
					int iy = y + row;
					int ioffset;
					if (0 <= iy && iy < height)
						ioffset = iy * width;
					else
						ioffset = y * width;

					int moffset = cols * (row + rows2) + cols2;
					for (int col = -cols2; col <= cols2; col++) {
						float f = matrix[moffset + col];

						if (f != 0) {
							int ix = x + col;
							if (!(0 <= ix && ix < width))
								ix = x;

							int rgb = inPixels[ioffset + ix];
							r += f * ((rgb >> 16) & 0xff);
							g += f * ((rgb >> 8) & 0xff);
							b += f * (rgb & 0xff);
						}
					}
				}
				
				int ir = (int) (r + 0.5);
				int ig = (int) (g + 0.5);
				int ib = (int) (b + 0.5);
				outPixels[index++] = (ir << 16) | (ig << 8) | ib;
			}
		}
	}

}
