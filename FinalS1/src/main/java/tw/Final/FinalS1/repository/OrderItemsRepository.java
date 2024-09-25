package tw.Final.FinalS1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.OrderItems;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    // 您可以根據需求定義自訂查詢，例如根據訂單ID查詢訂單項目
}
