package tw.Final.FinalS1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.model.userInfoMedel;
import tw.Final.FinalS1.repository.UserInfoRepository;
import tw.Final.FinalS1.repository.UserRepository;

@Service
public class CMSuserServiceImpl implements CMSuserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
	@Override
	public String getAllUsers(int page, int size, String key, Model model) {
		// 创建分页请求
	    Pageable pageable = PageRequest.of(page, size);
	    Page<UserModel> userPage;

	    // 判断是否有搜索关键字
	    if (key == null || key.isEmpty()) {
	        // 如果没有搜索关键字，则获取所有用户
	        userPage = userRepository.findAll(pageable);
	    } else {
	    	System.out.println("Key Pattern: 前" + key);
	        userPage = userRepository.findByKeyword(key, pageable);
	        System.out.println("用户总数: " + userPage.getTotalElements());
	        System.out.println("用户内容: " + userPage.getContent());
	        System.out.println("Key Pattern: 後" + key); // 打印处理后的关键字
	        

	    }

	    // 将用户数据添加到模型中
	    model.addAttribute("usersPage", userPage.getContent());
	    model.addAttribute("currentPage", userPage.getNumber());
	    model.addAttribute("totalPages", userPage.getTotalPages());
	    model.addAttribute("totalElements", userPage.getTotalElements());
	    
	    // 获取所有用户信息（如果需要）
	    List<userInfoMedel> userInfos = userInfoRepository.findAll(); // 獲取使用者資料
	    model.addAttribute("userInfos", userInfos);

	    return "userManagement"; // 跳轉使用者資料頁
	}






}
