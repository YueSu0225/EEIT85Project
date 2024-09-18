package tw.Final.FinalS1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.Final.FinalS1.model.*;
import tw.Final.FinalS1.repository.*;
import tw.Final.FinalS1.service.finalUserService;


@RestController
@RequestMapping("/final")
public class ControllerFinaltest {

    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private cartRepository cartRepository;
//
//    @Autowired
//    private wishListRepository wishListRepository;
//
//    @Autowired
//    private UserInfoRepository userInfoRepository;


//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
//        // 创建用户
//        finalUserModel user = new finalUserModel();
//        user.setAccount(request.getAccount());
//        user.setPassword(request.getPassword());
//        user.setEmail(request.getEmail());
//        user.setProvider_id(null);
//        user.setGoogleId(null); // 如果不使用 Google 登录
//
//        // 保存用户
//        userRepository.save(user);
//
//        // 创建购物车和愿望清单
//        cartModel cart = new cartModel();
//        cart.setUser(user);
//        cartRepository.save(cart);
//
//        wishListModel wishList = new wishListModel();
//        wishList.setUser(user);
//        wishListRepository.save(wishList);
//
//        // 创建用户信息
//        userInfoMedel userInfo = new userInfoMedel();
//        userInfo.setUser(user);
//        userInfo.setName(request.getName());
//        userInfo.setGander(request.getGender());
//        userInfo.setPhone_number(Integer.parseInt(request.getPhone()));
//        userInfo.setAddress(request.getStreet());
//        userInfo.setBirthday(request.getBirthday());
//        userInfoRepository.save(userInfo);
//
//        return ResponseEntity.ok().body("Registration successful");
//    }
	
	@Autowired
	private finalUserService finalUserService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request){
		return finalUserService.registerUser(request);
	}

 
    
    @PostMapping("/checkaccount")
    public ResponseEntity<Boolean> checkaccount(@RequestBody RegisterRequest request) {
        String account = request.getAccount();
        if (account == null || account.isEmpty()) {
            return ResponseEntity.badRequest().body(false); // 或者返回錯誤信息
        }
        boolean exists = userRepository.existsByAccount(account);
        return ResponseEntity.ok(exists);
    }
}
