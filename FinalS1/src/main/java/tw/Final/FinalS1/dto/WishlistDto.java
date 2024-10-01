package tw.Final.FinalS1.dto;

public class WishlistDto {
	private Long wishlistId;
	private Long productId;
	private int quantity;
	
	public Long getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(Long wishlistId) {
		this.wishlistId = wishlistId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
