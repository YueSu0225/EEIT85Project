package tw.Final.FinalS1.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import tw.Final.FinalS1.dto.RegisterRequest;

@Service
public class emailCodeServiceImpl implements emailCodeService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
    private String generateRandomCode() {
        return String.format("%04d", new Random().nextInt(10000)); // 生成4位隨機數
    }
    
	@Override
	public ResponseEntity<Map<String, Object>> sendVerificationCode(String email){
	    Map<String, Object> response = new HashMap<>();
	    
        // 生成隨機驗證碼
        String emailCode = generateRandomCode();
		
        // 創建JWT
        String jwtToken = createToken(email, emailCode);
        
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		
        
        // 加載Thymeleaf模板
        Context context = new Context();
        context.setVariable("JWTemailCode", emailCode);
        String htmlContent = templateEngine.process("emailCode", context);
        
        // 配置信件内容
        helper.setTo(email);
        helper.setSubject("您的驗證碼");
        helper.setText(htmlContent, true); // 設置為HTML内容
        
        // 發送信件
        javaMailSender.send(message);
       
        response.put("success", true);
        response.put("message", "发送成功");
        response.put("jwtToken", jwtToken);
        return ResponseEntity.ok(response);
  
        
		} catch (MessagingException e) {
	        e.printStackTrace(); 
	        response.put("success", false);
	        response.put("message", "發送失敗: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

		}
    }
	
    // 加密
    private final String secret = "i-am-a-supper-handsome-man-ilovemywifeandson";
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

	
	@Override
	public String createToken(String email, String verificationCode) {
        return Jwts.builder()
                .setSubject(email)
                .claim("code", verificationCode)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5分鐘過期
                .signWith(secretKey, SignatureAlgorithm.HS256) // 使用SecretKey加密
                .compact();
	}
	@Override
	public boolean validateToken(String token, String code) {
	    try {
	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(secretKey) // 使用SecretKey解密
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	        String tokenCode = claims.get("code", String.class);  // 獲取JWT中的驗證碼
	        Date expirationDate = claims.getExpiration(); // 獲取JWT的過期時間
	        long remainingTime = expirationDate.getTime() - System.currentTimeMillis(); // 剩餘有效時間（毫秒）

	        System.out.println("Token Code: " + tokenCode); // 打印从token中提取的驗證碼
	        System.out.println("User Input Code: " + code);   // 打印用戶輸入的驗證碼
	        // 打印剩下有效時間
	        if (remainingTime > 0) {
	            System.out.println("Remaining time until expiration: " + remainingTime / 1000 + " seconds"); // 剩餘的時間（秒）
	        } else {
	            System.out.println("Token has expired."); // 如果過期
	        }
	        
	        boolean isValid = code.equals(tokenCode) && !claims.getExpiration().before(new Date());  // 驗證驗證碼和過期時間
       
	        System.out.println("Validation Result: " + isValid); // 打印驗證结果
	        return isValid;
	    } catch (Exception e) {
	        return false;  // 如果token無效或過期
	    }
    
	}



	@Override
	public ResponseEntity<Map<String, Object>> processVerification(Map<String, String> request) {
	        String token = request.get("token"); // 獲取請求中的JWT token
	        String code = request.get("code");   // 獲取使用者輸入的驗證碼
	        System.out.println("inin");
	        Map<String, Object> response = new HashMap<>();
	        
	        // 調用validateToken方法驗證碼驗證
	        if (validateToken(token, code)) {
	            response.put("success", true);
	            response.put("message", "驗證成功！");
	        } else {
	            response.put("success", false);
	            response.put("message", "驗證失敗，請檢查驗證碼。");
	            System.out.println("erro2: 驗證碼輸入錯誤");
	        }
	        
	        return ResponseEntity.ok(response);
	    }

	}
		



