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
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.CartItemsRepository;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.ProductVariantRepository;
import tw.Final.FinalS1.repository.UserRepository;

@Service
public class ShoppingCartServiceDto {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    

    public CartModel addTocart(CartItemsDto cartRequest, String userUUID) {
//        Long cartId = cartRequest.getId();
        Long variantId = cartRequest.getVariantId();
        int quantity = cartRequest.getQuantity();
        
     // 根據 userUUID 查找使用者
        UserModel user = userRepository.findByUuid(userUUID);
        if(user == null) {
        	 throw new RuntimeException("User not found");
        }
        
        // 确保购物车存在
        CartModel cart = cartRepository.findById(user.getId())
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
                int price = productVariant.getPrice() * item.getQuantity();
                item.setPrice(price);
                // 以下為bigDecimal 計算有小數點的方法
//                 item.setPrice(productVariant.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                itemExists = true;
                break;
            }
        }

        // 如果商品不存在，则添加新商品
        if (!itemExists) {
            CartItemsModel newItem = new CartItemsModel();
            int price = productVariant.getPrice() * quantity;
            newItem.setCart(cart);
            newItem.setProductVariant(productVariant);
            newItem.setQuantity(quantity);
            newItem.setPrice(price);
//            newItem.setPrice(productVariant.getPrice().multiply(BigDecimal.valueOf(newItem.getQuantity())));
            cart.getCartItems().add(newItem);
        }

        return cartRepository.save(cart);
    }
    
    
    public CartModel updateCart(CartItemsDto cartRequest, String userUUID) {
//    	Long cartId = cartRequest.getId();
        Long variantId = cartRequest.getVariantId();
        int newquantity = cartRequest.getQuantity();
        
        UserModel user = userRepository.findByUuid(userUUID);
        if(user == null) {
        	 throw new RuntimeException("User not found");
        }
        
        CartModel cart = cartRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // 購物車中的商品
        CartItemsModel itemsToUpdate = cart.getCartItems().stream()
        						.filter(item -> Objects.equals(item.getProductVariant().getId(), variantId))
        						.findFirst()
        						.orElseThrow(() -> new RuntimeException("Item not found in cart"));
        // 更新或移除
        if(newquantity > 0) {
        	itemsToUpdate.setQuantity(newquantity);
        	int newprice = itemsToUpdate.getProductVariant().getPrice() * newquantity;
        	itemsToUpdate.setPrice(newprice);
//        	itemsToUpdate.setPrice(itemsToUpdate.getProductVariant().getPrice().multiply(BigDecimal.valueOf(newquantity)));
        }else {
        	cart.getCartItems().remove(itemsToUpdate);
        }
        return cartRepository.save(cart);
    }
    
    
    public void deleteCartItem(CartItemsDto cartRequest, String userUUID) {
//    	 Long cartId = cartRequest.getId();
         Long variantId = cartRequest.getVariantId();
    	
         UserModel user = userRepository.findByUuid(userUUID);
         if(user == null) {
         	 throw new RuntimeException("User not found");
         }
         
         CartModel cart = cartRepository.findById(user.getId())
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
    
    
    public List<CartItemsModel> getCartItems(Long cartId, String userUUID){
    	
    	UserModel user = userRepository.findByUuid(userUUID);
        if(user == null) {
        	 throw new RuntimeException("User not found");
        }
        
        CartModel cart = cartRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
		
		return cart.getCartItems();
	}
    
    public int getTotalPrice(Long cartId, String userUUID) {
    	
    	UserModel user = userRepository.findByUuid(userUUID);
        if(user == null) {
        	 throw new RuntimeException("User not found");
        }
        
        CartModel cart = cartRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
				
//	return cart.getCartItems().stream()
//			.map(CartItemsModel::getPrice)
//			.reduce(BigDecimal.ZERO, BigDecimal::add);
	
	 return cart.getCartItems().stream()
	            .mapToInt(CartItemsModel::getPrice)  // 假設 getPrice 返回的是 int 型別
	            .sum();  // 直接對整數進行加總
    }
}
