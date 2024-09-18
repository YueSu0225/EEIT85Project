package tw.Final.FinalS1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import tw.Final.FinalS1.model.RegisterRequest;

import tw.Final.FinalS1.model.finalUserModel;

public interface finalUserService {
	
	public ResponseEntity<String> registerUser(RegisterRequest request);
	
	public ResponseEntity<Boolean> checkaccount(RegisterRequest request);
		
}
