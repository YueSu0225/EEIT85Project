package tw.Final.FinalS1.dto;

public class WishlistDto {
	private Long wishlistId;
	private Long variantId;
	private int quantity;
	
	public Long getId() {
		return wishlistId;
	}
	public void setId(Long wishlistId) {
		this.wishlistId = wishlistId;
	}
	public Long getVariantId() {
		return variantId;
	}
	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
