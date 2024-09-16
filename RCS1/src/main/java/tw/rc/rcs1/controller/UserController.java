package tw.rc.rcs1.controller;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.rc.rcs1.model.User;
import tw.rc.rcs1.model.UserReturn;
import tw.rc.rcs1.service.UserSevice;

@RestController
public class UserController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserSevice userSevice;
	
	@PostMapping("/addUser")
	public UserReturn addUser(@RequestBody User user) {		
			return userSevice.addUser(user);
		
	}
	
	@RequestMapping("/user")
	public void rc02(@RequestParam String account,
					@RequestParam String passwd,
					@RequestParam String name) {
		String sql = "INSERT INTO user (account,passwd,name) VALUES (?,?,?)";
		jdbcTemplate.update(sql,account,passwd,name);
	}
	
	@PostMapping("/user/updateIcon/{id}")
	public void updateIcon(@PathVariable Long id, MultipartFile icon) {
		String name = icon.getName();
		String type =  icon.getContentType();
		long size = icon.getSize();
		System.out.println(String .format("%s:%s:%d", name,type,size));

		String sql = "UPDATE user SET icon = ? WHERE id = ?";
		try {
			jdbcTemplate.update(sql, icon.getBytes(), id);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	@PostMapping("/user/reg")
	public void userReg(@RequestParam String account,
			@RequestParam String passwd,
			@RequestParam String name,
			MultipartFile icon) {

		String sql = "INSERT INTO user (account,passwd,name,icon) VALUES (?,?,?,?)";
		try {
			jdbcTemplate.update(sql, account, BCrypt.hashpw(passwd, BCrypt.gensalt()),
					name,icon.getBytes());
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userSevice.getUser(id);
		return user;
	}
	
	
}
