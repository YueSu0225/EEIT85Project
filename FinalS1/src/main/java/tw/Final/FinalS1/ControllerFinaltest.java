package tw.Final.FinalS1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tw.Final.FinalS1.model.*;
import tw.Final.FinalS1.repository.*;

@RestController
@RequestMapping("/final")
public class ControllerFinaltest {

    @Autowired
    private UserRepository finalUserRepository;

    @Autowired
    private cartRepository cartRepository;

    @Autowired
    private wishListRepository wishListRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest request) {
        // 創建使用者
        finalUserModel user = new finalUserModel();
        user.setAccount(request.getAccount());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setProvider_id(null);
        user.setGoogleId(null); // 如果不使用 Google 登陸

        // 保存用户
        finalUserRepository.save(user);

        // 創建購物車與喜愛清單
        cartModel cart = new cartModel();
        cart.setUser(user);
        cartRepository.save(cart);

        wishListModel wishList = new wishListModel();
        wishList.setUser(user);
        wishListRepository.save(wishList);

        // 創建使用者資訊
        userInfoMedel userInfo = new userInfoMedel();
        userInfo.setUser(user);
        userInfo.setName(request.getName());
        userInfo.setGander(request.getGender());
        userInfo.setPhone_number(Integer.parseInt(request.getPhone()));
        userInfo.setAddress(request.getStreet());
        userInfo.setBirthday(request.getBirthday());
        userInfoRepository.save(userInfo);

        Map<String, String> response = new HashMap<>();
        response.put("success", "true"); // 使用字符串表示成功
        response.put("message", "Registration successful");
        return ResponseEntity.ok(response);

    }
    
    @PostMapping("/checkaccount")
    public ResponseEntity<Boolean> checkaccount(@RequestBody RegisterRequest request) {
        String account = request.getAccount();
        if (account == null || account.isEmpty()) {
            return ResponseEntity.badRequest().body(false); // 或者返回錯誤信息
        }
        boolean exists = finalUserRepository.existsByAccount(account);
        return ResponseEntity.ok(exists);
    }
}
