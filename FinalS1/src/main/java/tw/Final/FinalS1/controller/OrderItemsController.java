//package tw.Final.FinalS1.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//import tw.Final.FinalS1.model.OrderItems;
//import tw.Final.FinalS1.service.OrderItemsService;
//
//@RestController
//@RequestMapping("/api/order-items")
//public class OrderItemsController {
//
//    @Autowired
//    private OrderItemsService orderItemService;
//
//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<List<OrderItems>> getOrderItems(@PathVariable Long orderId) {
//        List<OrderItems> items = orderItemService.getOrderItemsByOrderId(orderId);
//        return ResponseEntity.ok(items);
//    }
//
//    @PostMapping
//    public ResponseEntity<OrderItems> addOrderItem(@RequestBody OrderItems orderItem) {
//        OrderItems newItem = orderItemService.addOrderItems(orderItem);
//        return ResponseEntity.ok(newItem);
//    }
//
//    // 其他增刪改查的 API 可以放在這裡
//}
