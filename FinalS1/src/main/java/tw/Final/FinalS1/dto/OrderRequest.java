package tw.Final.FinalS1.dto;

import java.math.BigDecimal;

public class OrderRequest {

    private Long userId;        // 用戶ID
    private BigDecimal totalPrice;  // 訂單總價格

    // 無參構造函數
    public OrderRequest() {}

    // 全參構造函數
    public OrderRequest(Long userId, BigDecimal totalPrice) {
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

    // Getter 和 Setter 方法

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
