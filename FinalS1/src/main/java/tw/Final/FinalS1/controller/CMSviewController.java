package tw.Final.FinalS1.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tw.Final.FinalS1.dto.RegisterRequest;
import tw.Final.FinalS1.model.OrderItems;
import tw.Final.FinalS1.model.OrderModel;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.service.CMSuserService;
import tw.Final.FinalS1.service.OrderService;

@Controller
public class CMSviewController {

    @Autowired
    private CMSuserService cmSuserService;

    @Autowired
    private OrderService orderService; // 注入 OrderService

    // 这是信箱验证码的信件内容（与后台无关，勿动）
    @RequestMapping("/emailCode")
    public String emailCode() {
        return "emailCode";
    }

    @RequestMapping("/backed")
    public String backed() {
        return "backed";
    }

    // 用户管理相关方法
    @GetMapping("/users")
    public String getAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              @RequestParam(value = "key", required = false) String key,
                              Model model) {
        return cmSuserService.getAllUsers(page, size, key, model);
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute RegisterRequest request) {
        cmSuserService.updateUser(request);  // 调用 Service 更新用户信息
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        cmSuserService.deleteUserById(id);
        return "redirect:/users";
    }

    // 订单管理相关方法

    // 显示订单管理页面
    @GetMapping("/orders")
    public String getAllOrders(@RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int size,
                               @RequestParam(value = "key", required = false) String key,
                               Model model) {
        Page<OrderModel> ordersPage;
        Pageable pageable = PageRequest.of(page, size);

        if (key != null && !key.isEmpty()) {
            // 如果有搜索关键字，根据关键字搜索
            ordersPage = orderService.searchOrders(key, pageable);
        } else {
            // 否则，获取所有订单
            ordersPage = orderService.getAllOrders(pageable);
        }

        model.addAttribute("ordersPage", ordersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", ordersPage.getTotalPages());

        return "orderManagement"; // 返回订单管理的模板
    }

    // 获取订单详情（用于 AJAX 请求）
    
    @GetMapping("/orders/{id}/details")
    public String getOrderDetails(@PathVariable("id") Long orderId, Model model) {
        List<OrderItems> orderItems = orderService.getOrderItemsByOrderId(orderId);
        BigDecimal orderTotal = orderService.getOrderTotal(orderId);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("orderTotal", orderTotal);
        return "orderDetails :: orderDetailsContent";
    }



    // 更新订单状态
    @PostMapping("/orders/updateStatus")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/orders"; // 更新后重定向回订单管理页面
    }

    // 删除订单
    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
