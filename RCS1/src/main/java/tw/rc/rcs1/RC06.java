package tw.rc.rcs1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rc06")
@RestController
public class RC06 {
	
	@RequestMapping("/m1")//輸入localhost:8080/rc06/m1
	public String rc06() {

		
		
		return "rc06";
	}
}
