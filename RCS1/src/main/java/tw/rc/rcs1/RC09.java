package tw.rc.rcs1;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@RestController
public class RC09 {
	
	@RequestMapping("/rc091")//驗證id是否為null,因為User類別裡面有設置id@NotNull
	public String rc091(@RequestBody @Valid User user) {
		System.out.println("OK1");
		return "rc091";
	}
	
	@RequestMapping("/rc092/{id}")//@Min(接收最小參數為<=),@Max(接收最大參數>=)
	public String rc092(@PathVariable @Min(10) @Max(100) Integer id) {
		System.out.println("rc092:" + id);
		return "rc092";
	}
	
	@RequestMapping("/rc093")
	public String rc093(@RequestBody @Valid User user) {
		System.out.println("rc093:" + user.getName());
		return "rc093";
	}
	
	@RequestMapping("/rc094")
	public String rc094(@RequestBody @Valid User user) {
		System.out.println("rc094:" + user.getName() + ":" +user.getEmail());
		return "rc094";
	}
	
	@RequestMapping("/rc095")
	public String rc095(@RequestBody @Valid User user) {
		System.out.println("rc095:" + user.getName() + ":" +user.getTwid());
		return "rc095";
	}
	
}
