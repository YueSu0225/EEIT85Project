package tw.Final.FinalS1.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
                .orElseThrow(() -> new RuntimeException("Productvariant not found"));

        // 更新购物车中的商品
        boolean itemExists = false;
        for (CartItemsModel item : cart.getCartItems()) {
            if (Objects.equals(item.getProductVariant().getId(), variantId)){
                item.setQuantity(item.getQuantity() + quantity);
                // 更新價格
                item.setPrice(productVariant.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
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
            newItem.setPrice(productVariant.getPrice().multiply(BigDecimal.valueOf(newItem.getQuantity())));
            cart.getCartItems().add(newItem);
        }

        return cartRepository.save(cart);
    }
    
    
    public CartModel updateCart(CartItemsDto cartRequest) {
    	Long cartId = cartRequest.getId();
        Long variantId = cartRequest.getVariantId();
        int newquantity = cartRequest.getQuantity();

        // 确保购物车存在
        CartModel cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 購物車中的商品
        CartItemsModel itemsToUpdate = cart.getCartItems().stream()
        						.filter(item -> Objects.equals(item.getProductVariant().getId(), variantId))
        						.findFirst()
        						.orElseThrow(() -> new RuntimeException("Item not found in cart"));
        // 更新或移除
        if(newquantity > 0) {
        	itemsToUpdate.setQuantity(newquantity);
        	itemsToUpdate.setPrice(itemsToUpdate.getProductVariant().getPrice().multiply(BigDecimal.valueOf(newquantity)));
        }else {
        	cart.getCartItems().remove(itemsToUpdate);
        }
        return cartRepository.save(cart);
    }
    
    
    
//    public void removeFromcart(CartItemsDto cartRequest) {
//   	 	Long cartId = cartRequest.getId();
//        Long variantId = cartRequest.getVariantId();
//        int quantity = cartRequest.getQuantity();
//        
//        CartModel cart = cartRepository.findById(cartId)
//                .orElseThrow(() -> new RuntimeException("Cart not found"));
//        
//        for (CartItemsModel item : cart.getCartItems()) {	    	
//            if (Objects.equals(item.getProductVariant().getId(), variantId)){  
//            	int currentQuantity = item.getQuantity();
//            	if(currentQuantity <= 0) {
//            		cart.getCartItems().remove(item);
//            	}else {
//            		item.setQuantity(currentQuantity - 1);
//            	}
//                break;
//            }
//        }
//		cartRepository.save(cart);
//	}
//    
    
    public void deleteCartItem(CartItemsDto cartRequest) {
    	 Long cartId = cartRequest.getId();
         Long variantId = cartRequest.getVariantId();
    	
		CartModel cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
		
		 // 查找要删除的商品项
	    CartItemsModel itemToDelete = cart.getCartItems().stream()
	            .filter(item -> Objects.equals(item.getProductVariant().getId(), variantId))
	            .findFirst()
	            .orElseThrow(() -> new RuntimeException("Item not found in cart"));

	    // 从购物车中移除商品项
	    cart.getCartItems().remove(itemToDelete);
		
		
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
    
    public BigDecimal getTotalPrice(Long userId) {
    	
	CartModel cart = cartRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found"));
				
	return cart.getCartItems().stream()
			.map(CartItemsModel::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	} 
}
