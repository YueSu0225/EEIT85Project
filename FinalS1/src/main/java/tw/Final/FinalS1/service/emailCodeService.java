package tw.Final.FinalS1.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import jakarta.mail.MessagingException;
import tw.Final.FinalS1.dto.RegisterRequest;

public interface emailCodeService {
	public ResponseEntity<Map<String, Object>> sendVerificationCode(String email);

	public ResponseEntity<Map<String, Object>> processVerification(Map<String, String> request);	 
    
    public String createToken(String email, String verificationCode);
    public boolean validateToken(String token, String code) ;
    
    

    	  
}
