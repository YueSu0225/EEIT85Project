package tw.Final.FinalS1.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//import tw.Final.FinalS1.dto.OrderRequest;
import tw.Final.FinalS1.dto.OrderResponse;
import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    // 創建訂單的端點
//    @PostMapping("/create")
//    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
//        // 使用 orderRequest 創建訂單
//        OrderModel order = orderService.createOrder(orderRequest.getUserId(), orderRequest.getTotalPrice());
//
//        // 將訂單實體轉換為 DTO
//        OrderResponse orderResponse = orderService.convertToOrderResponse(order);
//
//        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
//    }

    // 從購物車創建訂單
    @PostMapping("/create-from-cart")
    public ResponseEntity<OrderResponse> createOrderFromCart(@RequestParam Long userId, @RequestParam Long cartId) {
        OrderModel order = orderService.createOrderFromCart(userId, cartId);

        // 將訂單實體轉換為 DTO
        OrderResponse orderResponse = orderService.convertToOrderResponse(order);

        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    // 獲取訂單詳情的端點
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        OrderModel order = orderService.getOrder(orderId);

        // 將訂單實體轉換為 DTO
        OrderResponse orderResponse = orderService.convertToOrderResponse(order);

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    // 獲取用戶的所有訂單
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId) {
        List<OrderModel> orders = orderService.getOrdersByUserId(userId);

        // 將訂單列表轉換為 DTO 列表
        List<OrderResponse> orderResponses = orders.stream()
                .map(order -> orderService.convertToOrderResponse(order))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderResponses, HttpStatus.OK);
    }

    // 更新訂單狀態的端點
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status) {
        OrderModel updatedOrder = orderService.updateOrderStatus(orderId, status);
        
        // 將訂單實體轉換為 DTO
        OrderResponse orderResponse = orderService.convertToOrderResponse(updatedOrder);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    // 取消訂單的端點
    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
