package tw.rc.rcs1.controller;

import java.util.List;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tw.rc.rcs1.model.UserV2;
import tw.rc.rcs1.repository.UserRepository;
import tw.rc.rcs1.service.UserSevice;

@RestController
public class UserJpaController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserSevice userSevice;
	
	
	@PostMapping("/userjpa")
	public String add(@RequestBody UserV2 user) {
		userRepository.save(user);
		
		return "add";
	}
	
	@PostMapping("/userjpa2")
	public UserV2 add2(@RequestBody UserV2 user) {
		return userSevice.addUser(user);
	}
	
	@DeleteMapping("/userjpa/{id}")
	public String delUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "OK";
	}
	
	@PutMapping("/userjpa1/{id}")//id在url
	public String updateUser(@PathVariable Long id, @RequestBody UserV2 user) {
		user.setId(id);
		user.setPasswd(BCrypt.hashpw(user.getPasswd(), BCrypt.gensalt()));
		userRepository.save(user);
		
		return "userjpa1";
	}
	
	@PutMapping("/userjpa2")//id要寫在傳送的json格式里
	public String updateUser(@RequestBody UserV2 user) {
		userRepository.save(user);
		
		return "userjpa2";
	}
	
	@GetMapping("userjpa")
	public List<UserV2> query(){
		return (List<UserV2>)userRepository.findAll();
	}
	
}
