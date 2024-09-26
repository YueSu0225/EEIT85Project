package tw.Final.FinalS1.controller;

import java.math.BigDecimal;
import java.util.List;

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
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.service.ShoppingCartService;
import tw.Final.FinalS1.service.ShoppingCartServiceDto;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService cartService;
	@Autowired
	private ShoppingCartServiceDto shoppingCartServiceDto;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpSession session;
	
	@PostMapping("/add")
	public ResponseEntity<CartModel> addTocart(@RequestBody CartItemsDto cartRequest) {
//		 String userUUID = (String) session.getAttribute("userUUID");
//		 UserModel user = userRepository.findByUuid(userUUID);
//		 if(userUUID == null) {
//			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		 }
		
		try {
			 CartModel updateCart = shoppingCartServiceDto.addTocart(cartRequest);
			    return ResponseEntity.ok(updateCart);
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<CartModel> updateCart(@RequestBody CartItemsDto cartRequest) {
//		 String userUUID = (String) session.getAttribute("userUUID");
//		 if(userUUID == null) {
//			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		 }
		
		try {
			 CartModel updateCart = shoppingCartServiceDto.updateCart(cartRequest);
			    return ResponseEntity.ok(updateCart);
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> delegteCartItem(@RequestBody CartItemsDto cartRequest){
//		 String userUUID = (String) session.getAttribute("userUUID");
//		 if(userUUID == null) {
//			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		 }
		
		try {			
			shoppingCartServiceDto.deleteCartItem(cartRequest);
			return ResponseEntity.ok("Item successfully deleted");
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
	@GetMapping("/items/{id}")
	public ResponseEntity<List<CartItemsModel>> getCartItems(@PathVariable Long id){
//		 String userUUID = (String) session.getAttribute("userUUID");
//		 if(userUUID == null) {
//			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		 }
		
		List<CartItemsModel> cartItems = cartService.getCartItems(id);
			return ResponseEntity.ok(cartItems);
}
	
	@GetMapping("/total/{id}")
	public ResponseEntity<Integer> getTotalPrice(@PathVariable Long id){		
//		 String userUUID = (String) session.getAttribute("userUUID");
//		 if(userUUID == null) {
//			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//		 }
		
			int totalPrice = shoppingCartServiceDto.getTotalPrice(id);
			return ResponseEntity.ok(totalPrice);
	}
	
}
