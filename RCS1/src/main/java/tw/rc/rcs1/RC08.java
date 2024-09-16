package tw.rc.rcs1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * RESTful
 * POST : Create
 * GET : Read
 * PUT : Update
 * DELETE : Delete
 */

public class RC08 {
	@GetMapping("/users")//查詢ALL
	public String rc081() {
		return "rc081";
	}
	
	@GetMapping("/users/{id}")//查詢某id
	public String rc082(@PathVariable Integer id) {
		return "rc082";
	}
	
	@GetMapping("/users/{id}/bikes")//查詢某id的bikes
	public String rc083(@PathVariable Integer id) {
		return "rc083";
	}
	
	@GetMapping("/users/{id}/bikes/{bid}")//查詢某id的bikes的某bid
	public String rc084(@PathVariable Integer id, @PathVariable Integer bid) {
		return "rc084";
	}
	
	@PostMapping("/user")//新增
	public String rc085(@RequestBody User user) {
		return "rc085";
	}
	
	@PutMapping("/users/{id}")//修改id的XX
	public String rc086(@PathVariable Integer id,
			@RequestBody User user) {
		return "rc086";
	}
	
	@DeleteMapping("/users/{id}")//刪除id
	public String rc087(@PathVariable Integer id) {
		return "rc087";
	}
}