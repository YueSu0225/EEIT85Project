package tw.Final.FinalS1.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.ProductVariantRepository;

@Service
public class ShoppingCartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductVariantRepository productVariantRepository;
	
	public CartModel addTocart(Long userId, Long variantId, int quantity) {
		CartModel cart = cartRepository.findByUserId(userId).orElse(new CartModel());
		ProductVariant productVariant = productVariantRepository.findById(variantId)
							.orElseThrow(() -> new RuntimeException("Product not found"));
	
		boolean itemEsists = false;
		for(CartItemsModel item : cart.getCartItems()) {
			if(item.getProductVariant().getId().equals(variantId)) {
				item.setQuantity(item.getQuantity() + quantity);
				itemEsists = true;
				break;
			}
		}
	
		if(itemEsists) {
			CartItemsModel newItem = new CartItemsModel();
			newItem.setCart(cart);
			newItem.setProductVariant(productVariant);
			newItem.setQuantity(quantity);
			newItem.setPrice(productVariant.getPrice());
			cart.getCartItems().add(newItem);
		}
		return cartRepository.save(cart);
	}
	
	public void removeFromcart(Long userId, Long variantId, int quantity) {
		CartModel cart = cartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		cart.getCartItems().removeIf(item -> item.getProductVariant().getId().equals(variantId));
		cartRepository.save(cart);
	}
	
	public List<CartItemsModel> getCartItems(Long userId){
		CartModel cart = cartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.getCartItems();
	}
	
	public BigDecimal getTotalPrice(Long userID) {
		CartModel cart = cartRepository.findByUserId(userID)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.getCartItems().stream()
				.map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	
	}
	
	
	
	
	
	
	
}
