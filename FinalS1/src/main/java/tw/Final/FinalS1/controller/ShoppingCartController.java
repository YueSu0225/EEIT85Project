package tw.Final.FinalS1.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.Final.FinalS1.dto.CartItemsDto;
import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.service.ShoppingCartServiceDto;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartServiceDto shoppingCartServiceDto;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpSession session;
	@Autowired
	private CartRepository cartRepository;
	
	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> addTocart(@RequestBody CartItemsDto cartRequest, HttpSession session) {
		 String userUUID = (String) session.getAttribute("userUUID");
//		 UserModel user = userRepository.findByUuid(userUUID);
		 if(userUUID == null) {
			 System.out.println("用戶未登入");
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 }
		
		 System.out.println("收到的請求: " + cartRequest);
		 
		try {
			 CartModel updateCart = shoppingCartServiceDto.addTocart(cartRequest, userUUID);
			 
			 Map<String, Object> response = new HashMap<>();
			 response.put("success", true);
			 response.put("items", updateCart.getCartItems());
			 
			 return ResponseEntity.ok(response);
		}catch(RuntimeException e){
			System.out.println("錯誤: " + e.getMessage());
			
			 Map<String, Object> errorResponse = new HashMap<>();
		     errorResponse.put("success", false);
		     errorResponse.put("message", e.getMessage());
		        
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
	}
	
	@PostMapping("/update")
	public ResponseEntity<Map<String, Object>> updateCart(@RequestBody CartItemsDto cartRequest, HttpSession session) {
		 String userUUID = (String) session.getAttribute("userUUID");
//		 UserModel user = userRepository.findByUuid(userUUID);
		 if(userUUID == null) {
//			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("success", false));
		 }
		
		try {
			 CartModel updateCart = shoppingCartServiceDto.updateCart(cartRequest, userUUID);
			 
			 Map<String, Object> response = new HashMap<>();
		     response.put("success", true);
		     response.put("cartItems", updateCart.getCartItems());
			 
			 return ResponseEntity.ok(response);
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("success", false));
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delegteCartItem(@RequestBody CartItemsDto cartRequest, HttpSession session){
		 String userUUID = (String) session.getAttribute("userUUID");

		 if(userUUID == null) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 }
		
		try {			
			shoppingCartServiceDto.deleteCartItem(cartRequest, userUUID);
			return ResponseEntity.ok("Item successfully deleted");
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
	@GetMapping("/items")
	public ResponseEntity<List<CartItemsModel>> getCartItems(HttpSession session){
		 String userUUID = (String) session.getAttribute("userUUID");
//		 UserModel user = userRepository.findByUuid(userUUID);
		 if(userUUID == null) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 }
		
		List<CartItemsModel> cartItems = shoppingCartServiceDto.getCartItems(userUUID);
			return ResponseEntity.ok(cartItems);
}
	
	@GetMapping("/total/{id}")
	public ResponseEntity<Integer> getTotalPrice(@PathVariable Long id){		
		 String userUUID = (String) session.getAttribute("userUUID");
//		 UserModel user = userRepository.findByUuid(userUUID);
		 
		 if(userUUID == null) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		 }
		
			int totalPrice = shoppingCartServiceDto.getTotalPrice(id, userUUID);
			return ResponseEntity.ok(totalPrice);
	}
	
	
	
}
