package tw.Final.FinalS1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.Final.FinalS1.dto.OrderRequest;
import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.model.UserModel; // 確保這個導入
import tw.Final.FinalS1.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @PostMapping
//    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderRequest orderRequest) {
//        // 根據 userId 創建訂單
//        UserModel user = new UserModel();
//        user.setId(orderRequest.getUserId().intValue());  // 將 Long 轉換為 int
//        OrderModel order = orderService.createOrder(user, orderRequest.getTotalPrice());
//        return new ResponseEntity<>(order, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderRequest orderRequest) {
        // 直接傳遞 userId 給 orderService
        OrderModel order = orderService.createOrder(orderRequest.getUserId(), orderRequest.getTotalPrice());
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderModel> getOrder(@PathVariable Long orderId) {
        OrderModel order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderModel>> getUserOrders(@PathVariable Long userId) {
        List<OrderModel> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderModel> updateOrderStatus(@PathVariable Long orderId, @RequestBody String status) {
        OrderModel updatedOrder = orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
