package tw.Final.FinalS1.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.Final.FinalS1.dto.RegisterRequest;
import tw.Final.FinalS1.model.*;
import tw.Final.FinalS1.service.CMSuserService;
import tw.Final.FinalS1.service.UserService;
import tw.Final.FinalS1.service.emailCodeService;



@RestController
@RequestMapping("/final")
public class ControllerFinaltest {


	
	@Autowired
	private UserService finalUserService;
	
	@Autowired
	private emailCodeService emailCodeService;
	
	@Autowired
	private CMSuserService cmSuserService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterRequest request){
		return finalUserService.registerUser(request);
	}

    @PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody RegisterRequest request, HttpServletRequest servletRequest) {    	   	
    	return finalUserService.loginUser(request, servletRequest);
    }
    
    @GetMapping("/checkaccount")
    public ResponseEntity<Map<String, Object>> logincheck(@RequestParam String account){
    	return finalUserService.logincheck(account);
    }
    

    @GetMapping("/googlelogin/success")
    public void loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User, HttpServletRequest request,HttpServletResponse response) throws IOException {
        finalUserService.googleUser(oAuth2User,request);
        response.sendRedirect("/Home.html"); // 使用重定向到主頁
    }
    
    @GetMapping("/googlelogin")//亂寫的失敗訪問  接收到erro轉向主頁
    public void nothing(@RequestParam String erro , HttpServletResponse response) throws IOException {
        
        response.sendRedirect("/Home.html"); // 使用重定向到主頁
    }
    
    @GetMapping("/checksession")
    public ResponseEntity<Map<String, String>> sessionResponse(HttpSession session) {
    	
    	return finalUserService.sessionResponse(session);
    }
    
    @GetMapping("/userinfo")
    public ResponseEntity<Map<String, Object>> userinfo(HttpSession session){
    	return finalUserService.userinfo(session);
    }
    
    @PutMapping("/updateUserInfo")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody Map<String, String> userInfo, HttpSession session) {
        return finalUserService.updateUserInfo(userInfo, session);
    }
    @DeleteMapping("/deleteUser")
	public ResponseEntity<Map<String, String>> deleteUser(HttpSession session) {
    	return finalUserService.deleteUser(session);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        return finalUserService.logout(request);
    }
    
    @PostMapping("/sendCode")
    public ResponseEntity<Map<String, Object>> sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email"); // 從request獲取email
        System.out.println(email);
        return emailCodeService.sendVerificationCode(email);
    }
    
    @PostMapping("/verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody Map<String, String> request) {
        System.out.println("Request: " + request); // 打印請求信息

        return emailCodeService.processVerification(request); // 确保调用了processVerification
    }
    
    @PostMapping("/forgetpassword")
	public ResponseEntity<Map<String, Object>> forgetPasswordPassword(@RequestBody RegisterRequest request) {
	
    	return finalUserService.forgetPassword(request);
	}
    
    @PostMapping("/changepassword")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody RegisterRequest request, HttpSession session) {
        return finalUserService.changePassword(request, session);
    }
    
    @GetMapping("/orderdetails")
    public ResponseEntity<Map<String, Object>> orderDetails(HttpSession session){
    	return finalUserService.userOrderDetails(session);
    }
    
    
    @GetMapping("/getUserNameByUUID")
    public ResponseEntity<?> getUserNameByUUID(@RequestParam String uuid) {     
            String userName = cmSuserService.findUserNameByUUID(uuid); // 查詢使用者名字
            if (userName != null) {
                return ResponseEntity.ok(Map.of("success", true, "userName", userName));
            } else {
                return ResponseEntity.ok(Map.of("success", false, "message", "使用者不存在"));
            }

    }
}
