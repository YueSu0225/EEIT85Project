package tw.Final.FinalS1.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.Final.FinalS1.dto.OrderItemResponse;
import tw.Final.FinalS1.dto.OrderResponse;
import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.OrderItems;
import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.repository.CartItemsRepository;
import tw.Final.FinalS1.repository.CartRepository;
import tw.Final.FinalS1.repository.OrderItemsRepository;
import tw.Final.FinalS1.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartRepository cartRepository;

    // 創建訂單
    public OrderModel createOrder(Long userId, BigDecimal totalPrice) {
        OrderModel order = new OrderModel();
        order.setUserId(userId);  // 設置用戶ID
        order.setTotalPrice(totalPrice);
        order.setStatus("已付款");
        order.setEcpayNumber(generateEcpayNumber());  // 生成Ecpay編號
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // 生成Ecpay編號
    private String generateEcpayNumber() {
        return "ECPAY" + System.currentTimeMillis();
    }

    // 根據訂單ID取得訂單，並初始化 orderItems 集合
    @Transactional
    public OrderModel getOrder(Long orderId) {
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("找不到該訂單"));

        // 初始化 orderItems 集合
        Hibernate.initialize(order.getOrderItems());

        return order;
    }

    // 根據用戶ID取得訂單列表
    public List<OrderModel> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // 更新訂單狀態
    public OrderModel updateOrderStatus(Long orderId, String status) {
        OrderModel order = getOrder(orderId);
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // 取消訂單
    public void cancelOrder(Long orderId) {
        OrderModel order = getOrder(orderId);
        if ("已付款".equals(order.getStatus())) {
            order.setStatus("取消");
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
        } else {
            throw new RuntimeException("無法取消狀態為：" + order.getStatus() + " 的訂單");
        }
    }

    // 從購物車創建訂單
    @Transactional
    public OrderModel createOrderFromCart(Long userId, Long cartId) {
        // 步驟1：取得購物車項目
        List<CartItemsModel> cartItems = cartItemsRepository.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("購物車沒有商品");
        }

        // 步驟2：創建訂單並設定為已付款
        OrderModel order = new OrderModel();
        order.setUserId(userId);

        // 修正總價計算方式，將 int 轉換為 BigDecimal
        BigDecimal totalPrice = cartItems.stream()
                .map(item -> BigDecimal.valueOf(item.getPrice()))  // 將 int 轉換為 BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);
        order.setStatus("已付款");
        order.setEcpayNumber(generateEcpayNumber());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // 初始化 orderItems 集合（如果尚未初始化）
        if (order.getOrderItems() == null) {
            order.setOrderItems(new ArrayList<>());
        }

        // 步驟3：創建訂單項目並添加到訂單
        for (CartItemsModel cartItem : cartItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);  // 設定訂單關聯
            orderItem.setProductVariant(cartItem.getProductVariant());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(BigDecimal.valueOf(cartItem.getPrice()));  // 將 int 轉換為 BigDecimal
            order.getOrderItems().add(orderItem);  // 將訂單項目添加到訂單的集合中
            // 不需要單獨保存 orderItem，因為級聯設置會自動保存
        }

        // 步驟4：保存訂單（同時保存訂單項目）
        orderRepository.save(order);

        // 步驟5：清空購物車
        cartItemsRepository.deleteByCartId(cartId);

        // 步驟6：返回訂單資料
        return order;
    }

    // 將 OrderModel 轉換為 OrderResponse DTO
    public OrderResponse convertToOrderResponse(OrderModel order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream().map(orderItem -> {
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setId(orderItem.getId());
            itemResponse.setProductVariantId(orderItem.getProductVariant().getId());
            itemResponse.setProductName(orderItem.getProductVariant().getProduct().getName());
            itemResponse.setQuantity(orderItem.getQuantity());
            itemResponse.setPrice(orderItem.getPrice());
            itemResponse.setCreatedAt(orderItem.getCreatedAt());
            itemResponse.setUpdatedAt(orderItem.getUpdatedAt());
            return itemResponse;
        }).collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setEcpayNumber(order.getEcpayNumber());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setOrderItems(orderItemResponses);

        return orderResponse;
    }

}
