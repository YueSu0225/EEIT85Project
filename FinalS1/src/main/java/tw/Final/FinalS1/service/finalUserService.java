package tw.Final.FinalS1.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import tw.Final.FinalS1.model.RegisterRequest;

import tw.Final.FinalS1.model.finalUserModel;

public interface finalUserService {
	
	public ResponseEntity<Map<String, Object>> registerUser(RegisterRequest request);
	
	public ResponseEntity<Boolean> checkaccount(RegisterRequest request);
	
	public ResponseEntity<Map<String, Object>> loginUser(RegisterRequest request);
	
	public ResponseEntity<Map<String, Object>> logincheck(String account);
		
}
