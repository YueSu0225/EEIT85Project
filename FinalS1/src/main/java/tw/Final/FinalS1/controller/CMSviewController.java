package tw.Final.FinalS1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tw.Final.FinalS1.service.CMSuserService;

@Controller
public class CMSviewController {
	
	@Autowired
	private CMSuserService cmSuserService;
	

	//這是信箱驗證碼的信件內容((與後臺無關,勿動
	@RequestMapping("/emailCode")
	public String emailCode() {
		return "emailCode";
	}
	
	@RequestMapping("/backed")
	public String backed() {
		return "backed";
	}
	
	@GetMapping("/users")
    public String getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
            				@RequestParam(value = "size", defaultValue = "5") int size,
            				@RequestParam(value = "key", required = false) String key,
            				Model model) {
		return cmSuserService.getAllUsers(page, size, key, model);
	}
	
	
}
