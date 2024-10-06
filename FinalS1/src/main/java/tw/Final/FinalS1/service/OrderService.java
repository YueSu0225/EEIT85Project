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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    // 创建订单
    public OrderModel createOrder(Long userId, BigDecimal totalPrice) {
        OrderModel order = new OrderModel();
        order.setUserId(userId);  // 设置用户ID
        order.setTotalPrice(totalPrice);
        order.setStatus("已付款");
        order.setEcpayNumber(generateEcpayNumber());  // 生成Ecpay编号
//        order.setCreatedAt(LocalDateTime.now());
//        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // 生成Ecpay编号
    private String generateEcpayNumber() {
        return "ECPAY" + System.currentTimeMillis();
    }

    // 根据订单ID获取订单，并初始化 orderItems 集合
    @Transactional
    public OrderModel getOrder(Long orderId) {
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("找不到该订单"));

        // 初始化 orderItems 集合
        Hibernate.initialize(order.getOrderItems());

        return order;
    }

    // 根据用户ID获取订单列表
    public List<OrderModel> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // 获取所有订单，分页
    public Page<OrderModel> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    // 搜索订单，分页
    public Page<OrderModel> searchOrders(String keyword, Pageable pageable) {
        return orderRepository.searchOrders(keyword, pageable);
    }
    
    // 10/06 新增
 // 获取订单项列表
    public List<OrderItems> getOrderItemsByOrderId(Long orderId) {
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("订单未找到"));
        return order.getOrderItems();
    }

    // 获取订单总价
    public BigDecimal getOrderTotal(Long orderId) {
        List<OrderItems> orderItems = getOrderItemsByOrderId(orderId);
        return orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 更新订单状态
//    public void updateOrderStatus(Long orderId, String status) {
//        OrderModel order = getOrder(orderId);
//        order.setStatus(status);
//        order.setUpdatedAt(LocalDateTime.now());
//        orderRepository.save(order);
//    }
    
    public OrderModel updateOrderStatus(Long orderId, String status) {
        OrderModel order = getOrder(orderId);
        order.setStatus(status);
//        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // 删除订单
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // 取消订单
    public void cancelOrder(Long orderId) {
        OrderModel order = getOrder(orderId);
        if ("已付款".equals(order.getStatus())) {
            order.setStatus("取消");
//            order.setUpdatedAt(LocalDateTime.now());

            // 清空订单的订单项目集合，将会删除订单项目
            order.getOrderItems().clear();

            // 保存订单
            orderRepository.save(order);
        } else {
            throw new RuntimeException("无法取消状态为：" + order.getStatus() + " 的订单");
        }
    }

    // 从购物车创建订单
    @Transactional
    public OrderModel createOrderFromCart(Long userId, Long cartId) {
        // 步骤1：获取购物车项目
        List<CartItemsModel> cartItems = cartItemsRepository.findByCartId(cartId);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("购物车没有商品");
        }

        // 步骤2：创建订单并设置为已付款
        OrderModel order = new OrderModel();
        order.setUserId(userId);

        // 修正总价计算方式，将 int 转换为 BigDecimal
        BigDecimal totalPrice = cartItems.stream()
                .map(item -> BigDecimal.valueOf(item.getPrice()))  // 将 int 转换为 BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalPrice(totalPrice);
        order.setStatus("已付款");
        order.setEcpayNumber(generateEcpayNumber());
//        order.setCreatedAt(LocalDateTime.now());
//        order.setUpdatedAt(LocalDateTime.now());

        // 初始化 orderItems 集合（如果尚未初始化）
        if (order.getOrderItems() == null) {
            order.setOrderItems(new ArrayList<>());
        }

        // 步骤3：创建订单项目并添加到订单
        for (CartItemsModel cartItem : cartItems) {
            OrderItems orderItem = new OrderItems();
            orderItem.setOrder(order);  // 设置订单关联
            orderItem.setProductVariant(cartItem.getProductVariant());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(BigDecimal.valueOf(cartItem.getPrice()));  // 将 int 转换为 BigDecimal
            order.getOrderItems().add(orderItem);  // 将订单项目添加到订单的集合中
            // 不需要单独保存 orderItem，因为级联设置会自动保存
        }

        // 步骤4：保存订单（同时保存订单项目）
        orderRepository.save(order);

        // 步骤5：清空购物车
        cartItemsRepository.deleteByCartId(cartId);

        // 步骤6：返回订单数据
        return order;
    }

    // 将 OrderModel 转换为 OrderResponse DTO
    public OrderResponse convertToOrderResponse(OrderModel order) {
        List<OrderItemResponse> orderItemResponses = order.getOrderItems().stream().map(orderItem -> {
            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setId(orderItem.getId());
            itemResponse.setProductVariantId(orderItem.getProductVariant().getId());
            itemResponse.setProductName(orderItem.getProductVariant().getProduct().getName());
            itemResponse.setQuantity(orderItem.getQuantity());
            itemResponse.setPrice(orderItem.getPrice());
//            itemResponse.setCreatedAt(orderItem.getCreatedAt());
//            itemResponse.setUpdatedAt(orderItem.getUpdatedAt());
            return itemResponse;
        }).collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setEcpayNumber(order.getEcpayNumber());
//        orderResponse.setCreatedAt(order.getCreatedAt());
//        orderResponse.setUpdatedAt(order.getUpdatedAt());
        orderResponse.setOrderItems(orderItemResponses);

        return orderResponse;
    }

}
