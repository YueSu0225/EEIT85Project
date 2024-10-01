package tw.Final.FinalS1.service;

import org.springframework.ui.Model;

public interface CMSuserService {
	public String getAllUsers(int page, int size, String key, Model model);
}