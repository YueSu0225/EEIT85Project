//package tw.Final.FinalS1.service;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import tw.Final.FinalS1.model.CartItemsModel;
//import tw.Final.FinalS1.model.OrderItems;
//import tw.Final.FinalS1.model.OrderModel;
//import tw.Final.FinalS1.repository.CartItemsRepository;
//import tw.Final.FinalS1.repository.CartRepository;
//import tw.Final.FinalS1.repository.OrderItemsRepository;
//import tw.Final.FinalS1.repository.OrderRepository;
//
//@Service
//public class OrdersServiceV2 {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderItemsRepository orderItemsRepository;
//
//    @Autowired
//    private CartItemsRepository cartItemsRepository;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    // 創建訂單
//    public OrderModel createOrder(Long userId, BigDecimal totalPrice) {
//        OrderModel order = new OrderModel();
//        order.setUserId(userId);  // 設置用戶ID
//        order.setTotalPrice(totalPrice);
//        order.setStatus("已付款");
//        order.setEcpayNumber(generateEcpayNumber());  // 生成Ecpay編號
//        order.setCreatedAt(LocalDateTime.now());
//        order.setUpdatedAt(LocalDateTime.now());
//        return orderRepository.save(order);
//    }
//
//    // 生成Ecpay編號
//    private String generateEcpayNumber() {
//        return "ECPAY" + System.currentTimeMillis();
//    }
//
//    // 根據訂單ID取得訂單
//    public OrderModel getOrder(Long orderId) {
//        return orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("找不到該訂單"));
//    }
//
//    // 根據用戶ID取得訂單列表
//    public List<OrderModel> getOrdersByUserId(Long userId) {
//        return orderRepository.findByUserId(userId);
//    }
//
//    // 更新訂單狀態
//    public OrderModel updateOrderStatus(Long orderId, String status) {
//        OrderModel order = getOrder(orderId);
//        order.setStatus(status);
//        order.setUpdatedAt(LocalDateTime.now());
//        return orderRepository.save(order);
//    }
//
//    // 取消訂單
//    public void cancelOrder(Long orderId) {
//        OrderModel order = getOrder(orderId);
//        if ("已付款".equals(order.getStatus())) {
//            order.setStatus("取消");
//            order.setUpdatedAt(LocalDateTime.now());
//            orderRepository.save(order);
//        } else {
//            throw new RuntimeException("無法取消狀態為：" + order.getStatus() + " 的訂單");
//        }
//    }
//
//    // 根據訂單ID查詢訂單項目
//    public List<OrderItems> getOrderItemsByOrderId(Long orderId) {
//        return orderItemsRepository.findByOrderId(orderId);
//    }
//
//    // 新增訂單項目
//    public OrderItems addOrderItems(OrderItems orderItem) {
//        return orderItemsRepository.save(orderItem);
//    }
//
//    // 查詢所有訂單項目
//    public List<OrderItems> getAllOrderItems() {
//        return orderItemsRepository.findAll();
//    }
//
//    // 更新訂單項目
//    public OrderItems updateOrderItems(Long id, OrderItems orderItemDetails) {
//        Optional<OrderItems> optionalOrderItem = orderItemsRepository.findById(id);
//        if (optionalOrderItem.isPresent()) {
//            OrderItems orderItem = optionalOrderItem.get();
//            // 根據需求更新需要的欄位
//            orderItem.setProductVariant(orderItemDetails.getProductVariant());
//            orderItem.setQuantity(orderItemDetails.getQuantity());
//            orderItem.setPrice(orderItemDetails.getPrice());
//            return orderItemsRepository.save(orderItem);
//        } else {
//            throw new RuntimeException("找不到訂單項目，ID：" + id);
//        }
//    }
//
//    // 刪除訂單項目
//    public void deleteOrderItems(Long id) {
//        orderItemsRepository.deleteById(id);
//    }
//
//    // 從購物車創建訂單
//    @Transactional
//    public OrderModel createOrderFromCart(Long userId, Long cartId) {
//        // 步驟1：取得購物車項目
//        List<CartItemsModel> cartItems = cartItemsRepository.findByCartId(cartId);
//        if (cartItems.isEmpty()) {
//            throw new IllegalArgumentException("購物車沒有商品");
//        }
//
//        // 步驟2：創建訂單並設定為已付款
//        OrderModel order = new OrderModel();
//        order.setUserId(userId);  // 設定用戶ID
//
//        // 計算總價
//        BigDecimal totalPrice = cartItems.stream()
//                .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        order.setTotalPrice(totalPrice);
//        order.setStatus("已付款");
//        order = orderRepository.save(order);
//
//        // 步驟3：創建訂單項目並保存到資料庫
//        for (CartItemsModel cartItem : cartItems) {
//            OrderItems orderItem = new OrderItems();
//            orderItem.setOrder(order);  // 設定訂單模型
//            orderItem.setProductVariant(cartItem.getProductVariant());  // 設定產品變體
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setPrice(BigDecimal.valueOf(cartItem.getPrice()));  // 將 int price 轉換為 BigDecimal
//            orderItemsRepository.save(orderItem);
//        }
//
//        // 步驟4：清空購物車
//        cartItemsRepository.deleteByCartId(cartId);
//
//        // 步驟5：返回訂單資料
//        return order;
//    }
//}
