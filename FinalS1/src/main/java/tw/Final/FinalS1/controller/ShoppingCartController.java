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

import tw.Final.FinalS1.dto.CartItemsDto;
import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.service.ShoppingCartService;
import tw.Final.FinalS1.service.ShoppingCartServiceDto;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
	@Autowired
	private ShoppingCartService cartService;
	@Autowired
	private ShoppingCartServiceDto shoppingCartServiceDto;
	
	@PostMapping("/add")
	public ResponseEntity<CartModel> addTocart(@RequestBody CartItemsDto cartRequest) {
		try {
			 CartModel updateCart = shoppingCartServiceDto.addTocart(cartRequest);
			    return ResponseEntity.ok(updateCart);
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<CartModel> updateCart(@RequestBody CartItemsDto cartRequest) {
		try {
			 CartModel updateCart = shoppingCartServiceDto.updateCart(cartRequest);
			    return ResponseEntity.ok(updateCart);
		}catch(RuntimeException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CartModel> delegteCart(@PathVariable Long id, CartItemsDto cartRequest){
		shoppingCartServiceDto.deleteCartItem(cartRequest);
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/items")
	public ResponseEntity<List<CartItemsModel>> getCartItems(@RequestParam Long userId,
													@RequestParam Long variantId,				
													@RequestParam int quantity){
			List<CartItemsModel> cartItems = cartService.getCartItems(userId);
			return ResponseEntity.ok(cartItems);
}
	
	@GetMapping("/total")
	public ResponseEntity<BigDecimal> getTotalPrice(@RequestParam Long userId){		
			BigDecimal totalPrice = cartService.getTotalPrice(userId);
			return ResponseEntity.ok(totalPrice);
	}
	
}
