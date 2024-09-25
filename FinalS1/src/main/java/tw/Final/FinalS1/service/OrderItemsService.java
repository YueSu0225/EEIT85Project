//package tw.Final.FinalS1.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import tw.Final.FinalS1.model.OrderItems;
//import tw.Final.FinalS1.repository.OrderItemsRepository;
//
//@Service
//public class OrderItemsService {
//
//    @Autowired
//    private OrderItemsRepository orderItemRepository;
//
//    public List<OrderItems> getOrderItemsByOrderId(Long orderId) {
//        // 自定義邏輯查詢訂單項目
//        return orderItemRepository.findByOrderId(orderId);
//    }
//
//    public OrderItems addOrderItem(OrderItems orderItem) {
//        // 保存新訂單項目
//        return orderItemRepository.save(orderItem);
//    }
//
//    // 更多的業務邏輯處理可以在這裡添加
//}
