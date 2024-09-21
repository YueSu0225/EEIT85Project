package tw.Final.FinalS1.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody RegisterRequest request) {
    	return finalUserService.loginUser(request);
    }
    @GetMapping("/checkaccount")
    public ResponseEntity<Map<String, Object>> logincheck(@RequestParam String account){
    	return finalUserService.logincheck(account);
    }
    

    @GetMapping("/googlelogin/success")
    public void loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, HttpServletResponse response) throws IOException {
        finalUserService.googleUser(oAuth2User);
        response.sendRedirect("/Home.html"); // 使用重定向到主頁
    }
    
    @GetMapping("/googlelogin")//亂寫的失敗訪問  接收到erro轉向主頁
    public void nothing(@RequestParam String erro , HttpServletResponse response) throws IOException {
        
        response.sendRedirect("/Home.html"); // 使用重定向到主頁
    }

}
