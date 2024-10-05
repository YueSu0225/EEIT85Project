package tw.Final.FinalS1.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.Final.FinalS1.dto.WishlistDto;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.model.WishlistItemsModel;
import tw.Final.FinalS1.model.WishlistModel;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.repository.WishlistRepository;
import tw.Final.FinalS1.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	public WishlistService wishlistService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WishlistRepository wishlistRepository;

	@PostMapping("/toggle")
	public ResponseEntity<WishlistModel> toggleWishlistItem(@RequestBody WishlistDto wishlistRequest,
			HttpSession session) {
		Long productId = wishlistRequest.getProductId();
		String userUUID = (String) session.getAttribute("userUUID");
		System.out.println(userUUID);

		// 根據 UUID 查詢用戶
		UserModel user = userRepository.findByUuid(userUUID);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		// 查找喜愛清單
		WishlistModel wishlist = wishlistRepository.findById(user.getId())
				.orElseThrow(() -> new RuntimeException("Wishlist not found"));

		// 檢查商品是否已存在於喜愛清單中
		boolean itemExists = wishlist.getWishlistItems().stream()
				.anyMatch(item -> Objects.equals(item.getProduct().getId(), productId));

		if (itemExists) {
			// 如果商品存在，則移除
			WishlistModel updatedWishlist = wishlistService.deleteWishlist(wishlistRequest, session);
			return ResponseEntity.ok(updatedWishlist);
		} else {
			// 如果商品不存在，則添加
			WishlistModel updatedWishlist = wishlistService.addTowishlist(wishlistRequest, session);
			return ResponseEntity.ok(updatedWishlist);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<WishlistModel> addTowishlist(@RequestBody WishlistDto wishrequest, HttpSession session) {
		try {

			WishlistModel addwishlist = wishlistService.addTowishlist(wishrequest, session);
			return ResponseEntity.ok(addwishlist);

		} catch (RuntimeException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

//	@PostMapping("/update")
//	public ResponseEntity<WishlistModel> updateWishlist(@RequestBody WishlistDto wishrequest) {
//		try {
//
//			WishlistModel updateWishlist = wishlistService.updateWishlist(wishrequest);
//			return ResponseEntity.ok(updateWishlist);
//
//		} catch (RuntimeException e) {
//
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteWishlist(@PathVariable Long productId, HttpSession session) {
		String userUUID = (String) session.getAttribute("userUUID");
		if (userUUID == null) {
			throw new RuntimeException("User not authorized");
		}
		System.out.println("User UUID: " + userUUID);

		try {
			System.out.println("Received DELETE request for productId: " + productId);

			// 調用 deleteWishlistByProductId 方法
			wishlistService.deleteWishlistByProductId(productId, session);

			return ResponseEntity.ok("Item successfully deleted");
		} catch (RuntimeException e) {
			System.out.println("Error during deletion: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
		}
	}

	@GetMapping("/items")
	public ResponseEntity<?> getWishlistItems(HttpSession session) {
		// 從 session 中獲取 userUUID
		String userUUID = (String) session.getAttribute("userUUID");
		System.out.println("User UUID from session: " + userUUID);

		if (userUUID == null) {
			// 如果 session 中沒有 userUUID，返回未授權
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
		}

		// 根據 UUID 查詢用戶
		UserModel user = userRepository.findByUuid(userUUID);
		if (user == null) {
			// 如果找不到用戶，返回未授權
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		}

		// 調用 service 來獲取願望清單
		List<WishlistItemsModel> wishlistItems = wishlistService.getWishlistItems(user.getId());
		System.out.println("Wishlist items: " + wishlistItems);

		return ResponseEntity.ok(wishlistItems);
	}

}
