package tw.Final.FinalS1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CMSviewController {

	@RequestMapping("/backed")
	public String backed() {
		return "backed";
	}
	
	@RequestMapping("/emailCode")
	public String emailCode() {
		return "emailCode";
	}
}
