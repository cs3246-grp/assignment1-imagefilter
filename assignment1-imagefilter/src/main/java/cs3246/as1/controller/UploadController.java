package cs3246.as1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import javax.servlet.ServletConfig;
import java.io.FileOutputStream;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import cs3246.as1.bean.uploadItem;
import cs3246.as1.service.FilterService;

import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/uploadfile")
public class UploadController {
	@Autowired
	FilterService filterService;
	
	private String uploadFolderPath;
	private String imageName;
	ServletConfig config;

	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(Model model) {
		model.addAttribute(new uploadItem());
		return "uploadfile";
	}

	@RequestMapping(method = RequestMethod.POST)
	public void uploadImage(@RequestParam("file") MultipartFile file) {
		System.out.println("into update controller");
		uploadFolderPath = "/Users/popol/git/assignment1-imagefilter/assignment1-imagefilter/src/main/webapp/WEB-INF/images/";
		imageName = file.getOriginalFilename();
		try { if (file.getSize()>0) {
			String filePath = uploadFolderPath+file.getOriginalFilename();
			File fout = new File(filePath);
			if (!fout.exists()) fout.createNewFile();
			InputStream inputStream = file.getInputStream();
			OutputStream outputStream = new FileOutputStream(filePath);
			System.out.println("fileName:" + file.getOriginalFilename());

			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
		}} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(file.getOriginalFilename());
		System.out.println("leaver controller");
	}
	
	@RequestMapping(value = "filter", method = RequestMethod.POST)
	public @ResponseBody String doFilter(@RequestBody String filtertype) {
		
		return filterService.message();
	}
}
