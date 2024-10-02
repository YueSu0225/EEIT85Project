package tw.Final.FinalS1.service;

import org.springframework.ui.Model;

import tw.Final.FinalS1.dto.RegisterRequest;

public interface CMSuserService {
	public String getAllUsers(int page, int size, String key, Model model);
	
	public void updateUser(RegisterRequest request);
	
	public void deleteUserById(Long id);
}