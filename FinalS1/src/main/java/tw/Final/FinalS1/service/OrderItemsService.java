//package tw.Final.FinalS1.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import tw.Final.FinalS1.model.CartItemsModel;
//import tw.Final.FinalS1.model.OrderItems;
//import tw.Final.FinalS1.model.OrderModel;
//import tw.Final.FinalS1.repository.CartItemsRepository;
//import tw.Final.FinalS1.repository.CartRepository;
//import tw.Final.FinalS1.repository.OrderItemsRepository;
//import tw.Final.FinalS1.repository.OrderRepository;
//
//
//@Service
//public class OrderItemsService {
//
//    @Autowired
//    private OrderItemsRepository orderItemsRepository;
//
//    @Autowired
//    private CartItemsRepository cartItemsRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private OrderService orderService;
//
//    // 根據 orderId 查詢訂單項目
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
//            throw new RuntimeException("Order Item not found with id " + id);
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
//        // Step 1: 取得 CartItems
//        List<CartItemsModel> cartItems = cartItemsRepository.findByCartId(cartId);
//        if (cartItems.isEmpty()) {
//            throw new IllegalArgumentException("購物車沒有商品");
//        }
//
//        // Step 2: 創建 Order 並設定為已付款
//        OrderModel order = new OrderModel();
//        order.setUserId(userId);  // 設定用戶ID
//        // 由於 price 和 quantity 都是 int，需要使用 BigDecimal 來正確計算總價
//        BigDecimal totalPrice = cartItems.stream()
//                .map(item -> BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(item.getQuantity())))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        order.setTotalPrice(totalPrice);
//        order.setStatus("已付款");
//        order = orderRepository.save(order);
//
//        // Step 3: 創建 OrderItems 並保存到資料庫
//        for (CartItemsModel cartItem : cartItems) {
//            OrderItems orderItem = new OrderItems();
//            orderItem.setOrder(order);  // 設定訂單模型
//            orderItem.setProductVariant(cartItem.getProductVariant());  // 設定產品變體
//            orderItem.setQuantity(cartItem.getQuantity());
//            orderItem.setPrice(BigDecimal.valueOf(cartItem.getPrice()));  // 將 int price 轉換為 BigDecimal
//            orderItemsRepository.save(orderItem);
//        }
//
//        // Step 4: 清空購物車
//        cartItemsRepository.deleteByCartId(cartId);
//
//        // Step 5: 返回 Order 資料
//        return order;
//    }
//}
