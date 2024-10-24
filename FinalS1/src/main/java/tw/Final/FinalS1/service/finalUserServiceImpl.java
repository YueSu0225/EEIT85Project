package tw.Final.FinalS1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tw.Final.FinalS1.dto.RegisterRequest;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.model.OrderItems;
import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.model.userInfoMedel;
import tw.Final.FinalS1.model.WishlistModel;
import tw.Final.FinalS1.repository.UserInfoRepository;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.OrderItemsRepository;
import tw.Final.FinalS1.repository.OrderRepository;
import tw.Final.FinalS1.repository.WishlistRepository;


@Service
public class finalUserServiceImpl implements UserService{

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private WishlistRepository wishListRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	

	@Override
	public ResponseEntity<Map<String, Object>> registerUser(RegisterRequest request) {
		
		 // 檢查帳號是否已存在
	    List<UserModel> existingUser = userRepository.findByAccount(request.getAccount());
	    if (!existingUser.isEmpty()) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", false);
	        response.put("message", "信箱已被註冊");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
		 UserModel user = new UserModel();
	        user.setAccount(request.getAccount());
	        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
	        user.setEmail(request.getEmail());
	        user.setUuid(null);
	        user.setGoogleId(null); // 如果不使用 Google 登錄

	        // 保存用户
	        userRepository.save(user);

	        // 創建購物車
	        CartModel cart = new CartModel();
	        cart.setUser(user);
	        cartRepository.save(cart);
	        
	        // 創建喜愛清單
	        WishlistModel wishList = new WishlistModel();
	        wishList.setUser(user);
	        wishListRepository.save(wishList);

	        // 創建用戶資訊
	        userInfoMedel userInfo = new userInfoMedel();
	        userInfo.setUser(user);
	        userInfo.setName(request.getName());
	        userInfo.setPhone_number(request.getPhone());
	        userInfo.setAddress(request.getStreet());
	        userInfo.setBirthday(request.getBirthday());
	        userInfoRepository.save(userInfo);
	        
	        Map<String, Object> response = new HashMap<>();
	        response.put("success", true);
	        response.put("message", "Registration successful");
	        return ResponseEntity.ok(response);
	}


	@Override
	public ResponseEntity<Map<String, Object>> loginUser(RegisterRequest request, HttpServletRequest servletRequest) {
	    List<UserModel> existingUser = userRepository.findByAccount(request.getAccount());
	    Map<String, Object> response = new HashMap<>();
	    
	    // 檢查帳號是否存在
	    if (existingUser.isEmpty()) {	        
	        response.put("success", false);
	        response.put("message", "此帳號未註冊");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
	    
	    UserModel user = existingUser.get(0); // 資料庫獲取用戶
	    
	    // 檢查密碼是否正確
	    if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
	    	
			// 登入成功，插入 UUID 到資料庫
			String uuid = UUID.randomUUID().toString();
			// 使用方法userRepository 將 UUID 插入到資料庫
			user.setUuid(uuid);
			userRepository.save(user);
			 // 檢查用戶是否已有購物車
			CartModel userCart = cartRepository.findByUser(user); // 假設有個方法可以根據用戶 ID 查詢購物車
	        if (userCart == null) {
	            // 如果沒有購物車，則創建一個新的購物車
	        	userCart = new CartModel();
	            userCart.setUser(user);
	            cartRepository.save(userCart); // 保存新的購物車
	        }
			
	        // 設置 session
	        HttpSession session = servletRequest.getSession();
	        session.setAttribute("userUUID", uuid); // 將 UUID 存儲在 session 中


	        response.put("success", true);
	        response.put("message", "登入成功");
	        response.put("userUUID", uuid);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } else {
	        response.put("success", false);
	        response.put("message", "密碼不正確");
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	    }
	}

	@Override
	public ResponseEntity<Map<String, Object>> logincheck(String account) {
	    List<UserModel> user = userRepository.findByAccount(account);
        Map<String, Object> response = new HashMap<>();
        
        if (!user.isEmpty()) {
            response.put("exists", true);
        } else {
            response.put("exists", false);
        }

        return ResponseEntity.ok(response);
	}


	@Override
	public void googleUser(OAuth2User oAuth2User , HttpServletRequest request) {
        String email = oAuth2User.getAttribute("email"); // 獲取 Google 信箱
        String name = oAuth2User.getAttribute("name");   // 獲取 Google 姓名
        String googleId = oAuth2User.getAttribute("sub"); // 獲取 Google ID
	    List<UserModel> existGoogleId = userRepository.findByGoogleId(googleId);
	    String uuid = UUID.randomUUID().toString(); // 生成新的 UUID

	    if (!existGoogleId.isEmpty()) {
	        // 用戶已存在，更新其 UUID
	        UserModel user = existGoogleId.get(0);
	        user.setUuid(uuid); // 設定新的 UUID
	        userRepository.save(user); // 保存更改
	        System.out.println("User already exists: " );
	       
	    } else {
		 UserModel user = new UserModel();
	        user.setAccount(null);
	        user.setPassword(null);
	        user.setEmail(email);
	        user.setUuid(uuid);// 設定新的 UUID
	        user.setGoogleId(googleId); // 使用 Google 登錄
	        userRepository.save(user);

	        // 創建購物車
	        CartModel cart = new CartModel();
	        cart.setUser(user);
	        cartRepository.save(cart);
	        
	        // 創建喜愛清單
	        WishlistModel wishList = new WishlistModel();
	        wishList.setUser(user);
	        wishListRepository.save(wishList);

	        // 創建用戶資訊
	        userInfoMedel userInfo = new userInfoMedel();
	        userInfo.setUser(user);
	        userInfo.setName(name);
	        userInfo.setPhone_number(null);
	        userInfo.setAddress(null);
	        userInfo.setBirthday(null);
	        userInfoRepository.save(userInfo);

        
        System.out.println("User email: " + email);
        System.out.println("User name: " + name);
        System.out.println("User Google ID: " + googleId);
    
	    }
	 // 將新的 UUID 存儲到 Session 中
	    HttpSession session = request.getSession(); // 獲取 HttpSession;
	    session.setAttribute("userUUID", uuid);
	    System.out.println("User UUID: " + uuid);
	}


	@Override//這是在確認有無登入中,加上結帳後,點擊需要登入才能使用功能時,給一台購物車
	public ResponseEntity<Map<String, String>> sessionResponse(HttpSession session) {
	    String userUUID = (String) session.getAttribute("userUUID");
	    
	    if (userUUID != null) {
	        // 創建建一个 Map 来存返回的 UUID
	        Map<String, String> response = new HashMap<>();
	        response.put("userUUID", userUUID);
	        // 查詢用戶是否有購物車
	        UserModel user = userRepository.findByUuid(userUUID);
	        if (user != null) {
	            CartModel cart = cartRepository.findByUser(user);
	            if (cart == null) {
	                // 如果沒有購物車，創建一個新的購物車
	                cart = new CartModel();
	                cart.setUser(user); // 將用戶設置到購物車
	                cartRepository.save(cart); // 保存購物車到數據庫
	            }
	        }
	        return ResponseEntity.ok(response); // 返回 UUID
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // 未登录
	    }
	}


	@Override
	public ResponseEntity<Map<String, Object>> userinfo(HttpSession session) {
	    String userUUID = (String) session.getAttribute("userUUID");
	    
	    // 根據 UUID 查詢用戶
        UserModel user = userRepository.findByUuid(userUUID);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }
        
        boolean isGoogleLogin = user.getGoogleId() != null && !user.getGoogleId().isEmpty();


        
        // 查詢 userinfo
        userInfoMedel userInfo = new userInfoMedel();
        userInfo = userInfoRepository.findByUserId(user.getId());

        // 構建返回數據
        Map<String, Object> response = new HashMap<>();
        response.put("userEmail", user.getEmail());
        response.put("userInfoName", userInfo.getName());
        response.put("userInfoAddress", userInfo.getAddress());
        response.put("userInfoPhone", userInfo.getPhone_number());
        response.put("userInfoBirthday", userInfo.getBirthday());
        response.put("isGoogleLogin", isGoogleLogin); 

        return ResponseEntity.ok(response);
    }


	@Override
	public ResponseEntity<Map<String, Object>> updateUserInfo(Map<String, String> userInfo, HttpSession session) {
		 String userUUID = (String) session.getAttribute("userUUID");
	        
	        // 根據 UUID 查詢用戶
	        UserModel user = userRepository.findByUuid(userUUID);
	        if (user == null) {
	            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
	        }
	        
	        // 更新用戶信息
	        userInfoMedel existingUserInfo = userInfoRepository.findByUserId(user.getId());
	        if (existingUserInfo != null) {
	            existingUserInfo.setName(userInfo.get("name"));
	            existingUserInfo.setAddress(userInfo.get("address"));
	            existingUserInfo.setPhone_number(userInfo.get("phone"));
	            existingUserInfo.setBirthday(userInfo.get("birthday"));

	            userInfoRepository.save(existingUserInfo); // 保存更改
	        }

	        return ResponseEntity.ok(Map.of("success", true));
	    }


	@Override
	public ResponseEntity<Map<String, String>> deleteUser(HttpSession session) {
		 String userUUID = (String) session.getAttribute("userUUID");
	        
	        // 根據 UUID 查詢用戶
	        UserModel user = userRepository.findByUuid(userUUID);
	        if (user == null) {
	        	System.out.println("not found");
	            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
	        }
	        System.out.println(user);
	        userRepository.delete(user);
	        session.invalidate();
	        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
	}


	@Override
	public ResponseEntity<Void> logout(HttpServletRequest request) {
		 //獲取USER UUID 為誰
        String userId = (String) request.getSession().getAttribute("userUUID");

        if (userId != null) {
            UserModel user = userRepository.findByUuid(userId); // 查找用户
            if (user != null) {
                user.setUuid(""); // 設置UUID空字串
                userRepository.save(user); // 保存用户信息
            }
        }        
       
        request.getSession().invalidate();



        return ResponseEntity.ok().build(); // 返回 OK 
    }


	@Override
	public ResponseEntity<Map<String, Object>> forgetPassword(RegisterRequest request) {
		String account = request.getAccount();
		String newPassword = request.getPassword();
		
		List<UserModel> userModel = userRepository.findByAccount(account);
		UserModel user = userModel.get(0);
		user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
		
		userRepository.save(user);
		 
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
	}


	@Override
	public ResponseEntity<Map<String, Object>> changePassword(RegisterRequest request, HttpSession session) {
		 String userUUID = (String) session.getAttribute("userUUID");
	        UserModel user = userRepository.findByUuid(userUUID);
	        
	        if (BCrypt.checkpw(request.getOldPassword(), user.getPassword())) {	           
		        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
		        userRepository.save(user);
	        	Map<String, Object> response = new HashMap<>();
	            response.put("success", true);
	        	return ResponseEntity.ok(response);
	        }else {
	        	Map<String, Object> response = new HashMap<>();
	        	response.put("success", false);
		        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	        }
	
	}


	@Override
	public ResponseEntity<Map<String, Object>> userOrderDetails(HttpSession session) {
	    String userUUID = (String) session.getAttribute("userUUID");
	    UserModel user = userRepository.findByUuid(userUUID);
	    
	    // 檢查user是否存在
	    if (user == null) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("message", "User not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }

	    // 查詢與user所有訂單
	    List<OrderModel> orders = orderRepository.findByUserId(user.getId());
	    
	    // 如果沒有訂單回傳錯誤
	    if (orders.isEmpty()) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("message", "No orders found for this user");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }

	    
	    Map<String, Object> response = new HashMap<>();
	    List<Map<String, Object>> orderList = new ArrayList<>();

	    // 尋訪每個訂單
	    for (OrderModel order : orders) {
	        Map<String, Object> orderData = new HashMap<>();
	        orderData.put("orderNumber", order.getEcpayNumber());
	        orderData.put("totalPrice", order.getTotalPrice());
	        orderData.put("orderStatus", order.getStatus());

	        // 查詢訂單內的details
	        List<OrderItems> orderDetailsList = orderItemsRepository.findByOrderId(order.getId());
	        List<Map<String, Object>> orderDetails = new ArrayList<>();

	        // 尋訪每個訂單details加入map
	        for (OrderItems orderItem : orderDetailsList) {
	            Map<String, Object> itemDetails = new HashMap<>();
	            itemDetails.put("detailsQuantity", orderItem.getQuantity());
	            itemDetails.put("detailsPrice", orderItem.getPrice());
	            itemDetails.put("detailsName", orderItem.getProductVariant().getProduct().getName());
	            orderDetails.add(itemDetails);
	        }

	        // 如果訂單存在加入訂單details
	        if (!orderDetails.isEmpty()) {
	            orderData.put("orderItems", orderDetails);
	        }

	        // 將訂單加入訂單列表
	        orderList.add(orderData);
	    }

	    // 放進response回傳到前端
	    response.put("orders", orderList);

	    return ResponseEntity.ok(response);
	}

	
	



}
