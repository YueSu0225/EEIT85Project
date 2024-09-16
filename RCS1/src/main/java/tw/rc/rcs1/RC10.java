package tw.rc.rcs1;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 狀態碼
 * 200 (OK)
 * 500 (RunTimeException)
 */


@RestController
public class RC10 {
	
	@RequestMapping("/rc101")
	public ResponseEntity<User> rc101() {
		User user = new User();
		user.setId(12L);
		user.setName("哇哇哇");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
		//return 發送狀態碼 202,500之類的   ((針對回應的動作
	}
	
	@RequestMapping("/rc102")
	public String rc102() {
		// return "rc102";
		throw new RuntimeException();//拋出500
	}
	
	@RequestMapping("/rc103")
	public String rc103() throws FileNotFoundException{	
		throw new FileNotFoundException();
	}
	
	@RequestMapping("/rc104")
	public String rc104() throws IOException{	
		throw new IOException();
	}
	
}
