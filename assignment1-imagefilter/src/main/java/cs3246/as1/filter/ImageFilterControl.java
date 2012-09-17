package cs3246.as1.filter;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageFilterControl extends Canvas {
	BufferedImage src, dest;

	public ImageFilterControl(BufferedImage image) {
		src = image;
		dest = image;
	}

	public void applyFilter() {
		// BufferedImageOp blurFilter = new com.jhlabs.image.BoxBlurFilter(1, 1,
		// 1);

		/*
		 * int kWidth = 3; int kHeight = 3;
		 * 
		 * int xOffset = (kWidth - 1) / 2; int yOffset = (kHeight - 1) / 2;
		 * 
		 * BufferedImage ns = new BufferedImage(src.getWidth() + kWidth - 1,
		 * src.getHeight() + kHeight - 1, BufferedImage.TYPE_INT_ARGB);
		 * Graphics2D g2 = ns.createGraphics(); g2.drawImage(src, xOffset,
		 * yOffset, null); g2.dispose();
		 * 
		 * float[] blurMatrix = { 1/14f, 2/14f, 1/14f, 2/14f, 2/14f, 2/14f,
		 * 1/14f, 2/14f, 1/14f };
		 */

		// BufferedImageOp op = new BlurFilter();
		BufferedImageOp op = new OilPaintFilter(3);
		dest = op.filter(src, null);

		File outputfile = new File("saved.jpg");
		try {
			ImageIO.write(dest, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		repaint();

	}

	public void paint(Graphics g) {
		Dimension d = size();

		int x = (d.width - src.getWidth(this)) / 2;
		int y = (d.height - src.getHeight(this)) / 2;

		g.drawImage(dest, x, y, this);
	}

}
