package tw.Final.FinalS1;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.Final.FinalS1.model.*;
import tw.Final.FinalS1.service.finalUserService;


@RestController
@RequestMapping("/final")
public class ControllerFinaltest {


	
	@Autowired
	private finalUserService finalUserService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request){
		return finalUserService.registerUser(request);
	}
  
    @PostMapping("/checkaccount")
    public ResponseEntity<Boolean> checkaccount(@RequestBody RegisterRequest request) {
        return finalUserService.checkaccount(request);
    }
    @PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody RegisterRequest request) {
    	return finalUserService.loginUser(request);
    }
    @GetMapping("/checkaccount")
    public ResponseEntity<Map<String, Object>> logincheck(@RequestParam String account){
    	return finalUserService.logincheck(account);
    }
}
