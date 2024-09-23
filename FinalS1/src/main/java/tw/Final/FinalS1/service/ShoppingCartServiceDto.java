package tw.Final.FinalS1.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.Final.FinalS1.dto.CartItemsDto;
import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.repository.CartItemsRepository;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.ProductVariantRepository;

@Service
public class ShoppingCartServiceDto {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;
    
    

    public CartModel addTocart(CartItemsDto cartRequest) {
        Long cartId = cartRequest.getId();
        Long variantId = cartRequest.getVariantId();
        int quantity = cartRequest.getQuantity();

        // 确保购物车存在
        CartModel cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 查找产品变体
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 更新购物车中的商品
        boolean itemExists = false;
        for (CartItemsModel item : cart.getCartItems()) {
            if (item.getProductVariant().getId().equals(variantId)) {
                item.setQuantity(item.getQuantity() + quantity);
                itemExists = true;
                break;
            }
        }

        // 如果商品不存在，则添加新商品
        if (!itemExists) {
            CartItemsModel newItem = new CartItemsModel();
            newItem.setCart(cart);
            newItem.setProductVariant(productVariant);
            newItem.setQuantity(quantity);
            newItem.setPrice(productVariant.getPrice());
            cart.getCartItems().add(newItem);
        }

        return cartRepository.save(cart);
    }
    
    
    public void removeFromcart(CartItemsDto cartRequest) {
    	 Long cartId = cartRequest.getId();
         Long variantId = cartRequest.getVariantId();
         int quantity = cartRequest.getQuantity();
    	
		CartModel cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		cart.getCartItems().removeIf(item -> item.getProductVariant().getId().equals(variantId));
		cartRepository.save(cart);
	}
    
    public List<CartItemsModel> getCartItems(CartItemsDto cartRequest){
    	 Long cartId = cartRequest.getId();
         Long variantId = cartRequest.getVariantId();
         int quantity = cartRequest.getQuantity();
    	
		CartModel cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.getCartItems();
	}
    
    public BigDecimal getTotalPrice(CartItemsDto cartRequest) {
    	 Long cartId = cartRequest.getId();
         Long variantId = cartRequest.getVariantId();
         int quantity = cartRequest.getQuantity();
    	
		CartModel cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.getCartItems().stream()
				.map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	
	}
    
    
}
