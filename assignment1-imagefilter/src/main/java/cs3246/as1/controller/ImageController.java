package cs3246.as1.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

	@RequestMapping(value = "/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageName,HttpServletRequest request) {
		String filePath = request.getSession().getServletContext()
				.getRealPath("")
				+ "/WEB-INF/images/" + imageName;
		File file = new File(filePath);
		byte fileContent[] = new byte[(int)file.length()];
		try {
			FileInputStream fin = new FileInputStream(file);
			fin.read(fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileContent;
	}

}
