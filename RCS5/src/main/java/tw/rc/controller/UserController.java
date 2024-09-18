package tw.rc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.rc.model.ResponseUser;
import tw.rc.model.User;
import tw.rc.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/isexist/{account}")
	public ResponseUser isExist(@PathVariable String account) {
		return userService.isExistUser(account);
	}
	
	@PostMapping("/register")
	public ResponseUser reg(@RequestBody User user) {
		return userService.regUser(user);
	}
	
	@PostMapping("/registerV2")
	public ResponseUser regV2(@RequestParam("uploadFile") MultipartFile uploadFile,
			@RequestParam String account,
			@RequestParam String passwd,
			@RequestParam String name) {
		User user = new User();
		user.setAccount(account);
		user.setPasswd(passwd);
		user.setName(name);
		try {
			user.setIcon(uploadFile.getBytes());
		} catch (IOException e) {
			user.setIcon(null);
		}
		return userService.regUser(user);
	}
	
	@PostMapping("/login")
	public ResponseUser login(@RequestBody User user) {
		return userService.loginUser(user);
	}
}
