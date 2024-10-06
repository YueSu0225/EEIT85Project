package tw.Final.FinalS1.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import tw.Final.FinalS1.model.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByUserId(Long userId);  // 根据 user_id 查询订单

    // 根据关键字搜索订单
    @Query("SELECT o FROM OrderModel o WHERE CAST(o.id AS string) LIKE %:keyword% OR CAST(o.userId AS string) LIKE %:keyword%")
    Page<OrderModel> searchOrders(@Param("keyword") String keyword, Pageable pageable);
}
