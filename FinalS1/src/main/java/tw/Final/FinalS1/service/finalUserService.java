package tw.Final.FinalS1.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import tw.Final.FinalS1.model.RegisterRequest;

public interface finalUserService {
	
	public ResponseEntity<Map<String, Object>> registerUser(RegisterRequest request);
		
	public ResponseEntity<Map<String, Object>> loginUser(RegisterRequest request);
	
	public ResponseEntity<Map<String, Object>> logincheck(String account);
	
	public void googleUser(OAuth2User oAuth2User);
		
}
