package tw.Final.FinalS1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")  // 指定對應的資料表名稱
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // 使用 user_id 而不是 order_id
    @JsonIgnore  // 直接忽略 user 序列化
    private UserModel user;  // 存放用戶ID
*/
    @Column(name = "user_id", nullable = false)  // 使用 user_id 而不是 user 模型
    private Long userId;  // 存放用戶ID
    
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "status", nullable = false)
    private String status;
    
    @Column(name = "ecpay_number", nullable = false)  // ecpay_number 必須有值
    private String ecpayNumber;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 無參構造函數（JPA要求）
    public OrderModel() {}

    // 全參構造函數，根據需要選擇是否使用
    public OrderModel(Long userId, BigDecimal totalPrice, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

 // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEcpayNumber() {
        return ecpayNumber;
    }

    public void setEcpayNumber(String ecpayNumber) {
        this.ecpayNumber = ecpayNumber;
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
}