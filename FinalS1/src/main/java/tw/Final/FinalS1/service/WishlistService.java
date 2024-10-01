package tw.Final.FinalS1.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.dto.WishlistDto;
import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.model.WishlistItemsModel;
import tw.Final.FinalS1.model.WishlistModel;
import tw.Final.FinalS1.repository.ProductRepository;
import tw.Final.FinalS1.repository.ProductVariantRepository;
import tw.Final.FinalS1.repository.WishistItemsRepository;
import tw.Final.FinalS1.repository.WishlistRepository;

@Service
public class WishlistService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private WishistItemsRepository wishistItemsRepository;
	
	public WishlistModel addTowishlist(WishlistDto wishrequest) {
		Long wishlistId = wishrequest.getWishlistId();
		Long productId = wishrequest.getProductId();

		// 確認喜愛清單
		WishlistModel wishlist = wishlistRepository.findById(wishlistId)
				.orElseThrow(() -> new RuntimeException("wishlist not found"));

		// 查找商品
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));

		// 更新购物车中的商品
		boolean itemExists = wishlist.getWishlistItems().stream()
				.anyMatch(item -> Objects.equals(item.getProduct().getId(), productId));
		// 如果商品不存在，则添加新商品
		if (!itemExists) {
			WishlistItemsModel newItem = new WishlistItemsModel();
			newItem.setWishlist(wishlist);
			newItem.setProduct(product);
			wishlist.getWishlistItems().add(newItem);
		}
		return wishlistRepository.save(wishlist);
	}
	
//	public WishlistModel updateWishlist(WishlistDto wishrequest) {
//		Long wishlistId = wishrequest.getId();
//		Long variantId = wishrequest.getVariantId();
//		int newquantity = wishrequest.getQuantity();
//
//		WishlistModel wishlist = wishlistRepository.findById(wishlistId)
//				.orElseThrow(() -> new RuntimeException("wishlist not found"));
//		
//		WishlistItemsModel itemsToUpdate = wishlist.getWishlistItems().stream()
//				.filter(item -> Objects.equals(item.getProductVariant().getId(), variantId))
//				.findFirst()
//				.orElseThrow(() -> new RuntimeException("Item not found in wishlist"));
//
//		if(newquantity > 0) {
//			itemsToUpdate.setQuantity(newquantity);
//			int newprice = itemsToUpdate.getProductVariant().getPrice() * newquantity;
//        	itemsToUpdate.setPrice(newprice);
//		}else {
//			wishlist.getWishlistItems().remove(newquantity);
//		}
//		return wishlistRepository.save(wishlist);
//	}
	
	public WishlistModel deleteWishlist(WishlistDto wishrequest) {
		Long wishlistId = wishrequest.getWishlistId();
		Long productId = wishrequest.getProductId();

		WishlistModel wishlist = wishlistRepository.findById(wishlistId)
				.orElseThrow(() -> new RuntimeException("Wishlist not found"));
		
		WishlistItemsModel itemToDelete = wishlist.getWishlistItems().stream()
										.filter(item -> Objects.equals(item.getProduct().getId(), productId))
							            .findFirst()
							            .orElseThrow(() -> new RuntimeException("Item not found in wishlist"));
		
		wishlist.getWishlistItems().remove(itemToDelete);
		
		return wishlistRepository.save(wishlist);
		
	}
	
	public List<WishlistItemsModel> getWishlistItems(Long wishlistId) {
		
		WishlistModel wishlist = wishlistRepository.findById(wishlistId)
				.orElseThrow(() -> new RuntimeException("Wishlist not found"));
		
		return wishlist.getWishlistItems();
	}
	
	
}
