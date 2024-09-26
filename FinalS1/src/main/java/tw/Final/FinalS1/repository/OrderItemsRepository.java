package tw.Final.FinalS1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    // 根據 orderId 查詢訂單項目
    List<OrderItems> findByOrderId(Long orderId);}
