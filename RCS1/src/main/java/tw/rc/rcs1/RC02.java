package tw.rc.rcs1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RC02 {
	@Autowired
	private MySetting mySetting;
	
	@RequestMapping("/rc02")
	public String rc02() {
		return String.format("<h1>%s</h2>", mySetting.getTitle());
	}
}
