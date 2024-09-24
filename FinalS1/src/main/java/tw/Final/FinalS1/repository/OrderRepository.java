package tw.Final.FinalS1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.Final.FinalS1.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByOrderId(Long orderId);  // 根據 order_id 查詢訂單
}
