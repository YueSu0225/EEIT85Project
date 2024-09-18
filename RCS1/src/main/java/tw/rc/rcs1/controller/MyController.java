package tw.rc.rcs1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/*
 *  注意: thymeleaf  要使用 Controller
 */
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MyController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/home")
	public String home() {
		return "home"; //  thymeleaf -> templates  -> xxx.html
	}
	
	//若是沒有@requestmapping@getmapping的話會在static找xxx.html
	
	@GetMapping("/rc01")
	public String rc01() {
		return "rc01"; 	
		}
	
	@GetMapping("/rc02")
	public String brad02() {
		return "rc02";	
		}
	@GetMapping("/user/reg")
	public String reg() {
		return "reg";	
		}


}
