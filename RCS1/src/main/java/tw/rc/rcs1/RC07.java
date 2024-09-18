package tw.rc.rcs1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * RequestParam 參數接收((問號後面 => url?x=10&y=3
 * RequestBody 傳遞內容
 * RequestHeaders 傳遞方式Content-Type
 * PathVariable 這是Restful的方式
 */
@RestController
public class RC07 {
	
	@RequestMapping("/rc071")//這是使用action表單傳送 才會使用這種代參數的接收方式
	public String rc071(@RequestParam(name = "xxx") Integer x,//name是在url上要寫的參數
			@RequestParam(required = false, defaultValue = "0") Integer y, //required = false ->代表不一定需要代
						//RequestParam(這是使用servlet傳遞參數的型別,都是字串)是因為外面的Integer轉型別												//defaultValue = "0" ->代表預設為0	
			@RequestParam String name) {//接收參數沒順序,只看參數以及參數決定的型別
		int r = x + y;
		System.out.println(name + ":" + r);
		return "rc071";
	}
	@RequestMapping("/rc072")//接收是json格式
	public String rc072(@RequestBody User user) {
		System.out.println(user.getId() + ":" + user.getName());
	
		return "rc072";
				
	}
	@RequestMapping("/rc073")
	public String rc073(@RequestHeader(name = "Content-Type") String contentType, 
			@RequestBody User user) {
		System.out.println(contentType + ":" + user.getId() + ":" + user.getName());
		return "rc073";
	}
	@RequestMapping("/rc074/user/{id}")//使用Restful風格
	public String rc074(@PathVariable Integer id) {
		System.out.println(id);
		return "rc074";
	}
}