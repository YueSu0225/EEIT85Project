package tw.Final.FinalS1.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderItemResponse {

    private Long id;
    private Long productVariantId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 無參構造函數
    public OrderItemResponse() {}

    // 全參構造函數
    public OrderItemResponse(Long id, Long productVariantId, String productName, int quantity, BigDecimal price,
                             LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.productVariantId = productVariantId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(Long productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

    // Getter 和 Setter 方法（根據需要添加）
    
    
}
