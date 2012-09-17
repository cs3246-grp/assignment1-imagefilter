package cs3246.as1.service;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import cs3246.as1.filter.BlurFilter;
import cs3246.as1.filter.CompoundFilter;
import cs3246.as1.filter.HdrBuilder;
import cs3246.as1.filter.LomoFilter;
import cs3246.as1.filter.OilPaintFilter;
import cs3246.as1.filter.OldFilter;

@Service
public class FilterService {
	String uploadFolderPath = "/Users/popol/git/assignment1-imagefilter/assignment1-imagefilter/src/main/webapp/WEB-INF/images/";

	public String filterImage(String imageName, String filtertype) {
		BufferedImage src, dest;
		File image = new File(uploadFolderPath+imageName);
		try {
			src = ImageIO.read(image);
			BufferedImageOp op;
			if (filtertype.equals("blur"))
				op = new BlurFilter();
			else if (filtertype.equals("compound"))
				op = new CompoundFilter(new OilPaintFilter(10), new OldFilter());
			else if (filtertype.equals("lomo"))
				op = new LomoFilter();
			else if (filtertype.equals("oil"))
				op = new OilPaintFilter(15);
			else 
				op = new OldFilter();
			dest = op.filter(src, null);

			File outputfile = new File(uploadFolderPath + "saved.jpg");
			ImageIO.write(dest, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "saved.jpg";
	}

	public void filterImage(List<String> nameList) {
		try {
			HdrBuilder hdr = new HdrBuilder(nameList);
			BufferedImage dest = hdr.filter(null, null);
			String uploadFolderPath = "/Users/popol/git/assignment1-imagefilter/assignment1-imagefilter/src/main/webapp/WEB-INF/images/";
			File outputfile = new File(uploadFolderPath + "hdr.jpg");
			ImageIO.write(dest, "jpg", outputfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
