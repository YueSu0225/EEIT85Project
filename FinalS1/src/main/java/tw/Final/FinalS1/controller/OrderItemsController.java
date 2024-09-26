package tw.Final.FinalS1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import tw.Final.FinalS1.model.OrderItems;
import tw.Final.FinalS1.service.OrderItemsService;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemService;

    // 根據 orderId 查詢訂單項目
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItems>> getOrderItems(@PathVariable Long orderId) {
        List<OrderItems> items = orderItemService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }

    // 新增訂單項目
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<OrderItems> addOrderItem(@RequestBody OrderItems orderItem) {
        OrderItems newItem = orderItemService.addOrderItems(orderItem);
        return ResponseEntity.ok(newItem);
    }

    // 查詢所有訂單項目
    @GetMapping("/{id}")
    public ResponseEntity<List<OrderItems>> getAllOrderItems() {
        List<OrderItems> items = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(items);
    }

    // 更新訂單項目
    @PutMapping("/{id}")
    public ResponseEntity<OrderItems> updateOrderItem(@PathVariable Long id, @RequestBody OrderItems orderItemDetails) {
        OrderItems updatedItem = orderItemService.updateOrderItems(id, orderItemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    // 刪除訂單項目
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItems(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 其他增刪改查的 API 可以放在這裡
}
