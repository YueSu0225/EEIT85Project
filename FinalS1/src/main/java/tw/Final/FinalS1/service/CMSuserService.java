package tw.Final.FinalS1.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import tw.Final.FinalS1.model.UserModel;

public interface CMSuserService {
	public String getAllUsers(int page, int size, String key, Model model);

}
