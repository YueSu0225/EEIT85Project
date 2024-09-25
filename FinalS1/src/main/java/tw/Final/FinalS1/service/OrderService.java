package tw.Final.FinalS1.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderModel createOrder(UserModel user, BigDecimal totalPrice) {
        OrderModel order = new OrderModel();
        order.setUser(user);  // 設置 user 關聯
        order.setTotalPrice(totalPrice);
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public OrderModel getOrder(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderModel> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);  // 根據 user_id 查詢訂單
    }

    public OrderModel updateOrderStatus(Long orderId, String status) {
        OrderModel order = getOrder(orderId);
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public void cancelOrder(Long orderId) {
        OrderModel order = getOrder(orderId);
        if ("CREATED".equals(order.getStatus())) {
            order.setStatus("CANCELED");
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Cannot cancel order in status: " + order.getStatus());
        }
    }
}
