package tw.Final.FinalS1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.UserRepository;

@Service
public class CMSuserServiceImpl implements CMSuserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String getAllUsers(int page, int size, String key, Model model) {
		// 分頁
	    Pageable pageable = PageRequest.of(page, size);
	    Page<UserModel> userPage;
	    // 判斷搜索是否NULL
	    if (key == null || key.isEmpty()) {
	        // 如果NULL找出所有使用者
	        userPage = userRepository.findAll(pageable);
	    } else {
	        userPage = userRepository.findByKeyword(key, pageable);
	    }
	    // 將資料帶進MODEL
	    model.addAttribute("usersPage", userPage.getContent());
	    model.addAttribute("currentPage", userPage.getNumber());
	    model.addAttribute("totalPages", userPage.getTotalPages());
	    model.addAttribute("totalElements", userPage.getTotalElements());
	    
	    return "userManagement"; // 跳轉使用者資料頁
	}
}