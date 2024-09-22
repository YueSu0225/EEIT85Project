package tw.Final.FinalS1.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItemsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "cart_id")
	private CartModel cart;
	
	
	@ManyToOne
	@JoinColumn(name = "product_variant_id")
	private ProductVariant productVariant;
	
	private int quantity;
	private BigDecimal price;
	
//	public CartItemsModel() {}
//	
//	public CartItemsModel(CartModel cart, ProductVariant productVariant, int quantity) {
//		this.cart = cart;
//		this.productVariant = productVariant;
//		this.quantity = quantity;
//		this.price = productVariant.getPrice();
//	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public CartModel getCart() {
		return cart;
	}

	public void setCart(CartModel cart) {
		this.cart = cart;
	}

	public ProductVariant getProductVariant() {
		return productVariant;
	}

	public void setProductVariant(ProductVariant productVariant) {
		this.productVariant = productVariant;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
