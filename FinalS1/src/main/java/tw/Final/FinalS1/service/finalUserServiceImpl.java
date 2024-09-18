package tw.Final.FinalS1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.RegisterRequest;

import tw.Final.FinalS1.model.cartModel;
import tw.Final.FinalS1.model.finalUserModel;
import tw.Final.FinalS1.model.userInfoMedel;
import tw.Final.FinalS1.model.wishListModel;
import tw.Final.FinalS1.repository.UserInfoRepository;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.repository.cartRepository;
import tw.Final.FinalS1.repository.wishListRepository;

@Service
public class finalUserServiceImpl implements finalUserService{

	@Autowired
	private cartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private wishListRepository wishListRepository;
	
	

	@Override
	public ResponseEntity<String> registerUser(RegisterRequest request) {
		 finalUserModel user = new finalUserModel();
	        user.setAccount(request.getAccount());
	        user.setPassword(request.getPassword());
	        user.setEmail(request.getEmail());
	        user.setProvider_id(null);
	        user.setGoogleId(null); // 如果不使用 Google 登录

	        // 保存用户
	        userRepository.save(user);

	        // 创建购物车和愿望清单
	        cartModel cart = new cartModel();
	        cart.setUser(user);
	        cartRepository.save(cart);

	        wishListModel wishList = new wishListModel();
	        wishList.setUser(user);
	        wishListRepository.save(wishList);

	        // 创建用户信息
	        userInfoMedel userInfo = new userInfoMedel();
	        userInfo.setUser(user);
	        userInfo.setName(request.getName());
	        userInfo.setGander(request.getGender());
	        userInfo.setPhone_number(Integer.parseInt(request.getPhone()));
	        userInfo.setAddress(request.getStreet());
	        userInfo.setBirthday(request.getBirthday());
	        userInfoRepository.save(userInfo);
	        return ResponseEntity.ok("Registration successful");
	}

	@Override
	public ResponseEntity<Boolean> checkaccount(RegisterRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
