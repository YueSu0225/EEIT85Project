package tw.rc.rcs1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RC03 {
	@Value("${title}")//在resourse的properties設置
	private String title;
	
	@Value("${name:World}")//冒號後面是預設值,
	private String myname;
	
	@RequestMapping("/rc03")
	public String rc03() {
		return String.format("<h1>%s</h1><hr /><div>Hello, %s</div>", title, myname);
	}
	
}
