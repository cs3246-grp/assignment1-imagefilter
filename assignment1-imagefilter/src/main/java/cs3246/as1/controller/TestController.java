package cs3246.as1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs3246.as1.bean.Image;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	@RequestMapping(method = RequestMethod.GET)
	public String getUploadForm(Model model) {
		model.addAttribute(new Image());
		return "test";
	}
}
