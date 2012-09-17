package cs3246.as1.filter;

import java.awt.image.*;

/**
 * A BufferedImageOp which combines two other BufferedImageOps, one after the other.
 */
public class CompoundFilter extends AbstractBufferedImageOp {
	private BufferedImageOp filter1;
	private BufferedImageOp filter2;
	
	/**
     * Construct a CompoundFilter.
     * @param filter1 the first filter
     * @param filter2 the second filter
     */
    public CompoundFilter( BufferedImageOp filter1, BufferedImageOp filter2 ) {
		this.filter1 = filter1;
		this.filter2 = filter2;
	}
	
	public BufferedImage filter( BufferedImage src, BufferedImage dst ) {
		BufferedImage image = filter1.filter( src, dst );
		image = filter2.filter( image, dst );
		return image;
	}
}
