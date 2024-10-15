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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.*;


import tw.Final.FinalS1.dto.RegisterRequest;

import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.model.Category;
import tw.Final.FinalS1.model.Type;
import tw.Final.FinalS1.model.Size;
import tw.Final.FinalS1.model.Color;
import tw.Final.FinalS1.model.OrderItems;
import tw.Final.FinalS1.model.OrderModel;

import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.repository.UserRepository;
import tw.Final.FinalS1.service.CMSuserService;
import tw.Final.FinalS1.service.CategoryService;
import tw.Final.FinalS1.service.ColorService;
import tw.Final.FinalS1.service.ProductService;
import tw.Final.FinalS1.service.ProductVariantService;
import tw.Final.FinalS1.service.SizeService;
import tw.Final.FinalS1.service.TypeService;
import tw.Final.FinalS1.service.OrderService;


@Controller
public class CMSviewController {

	
	@Autowired
	private CMSuserService cmSuserService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductVariantService productVariantService;
	
	@Autowired
	private SizeService  sizeService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@Autowired
	private OrderService orderService; // 注入 OrderService
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	
	
	
	
	//--------------------------------------------
	// 產品清單頁面

	
	 @GetMapping("/productlist")
	    public String showProductList(	Model model) {
	        return "productlist"; // 返回模板的名称，即 productlist.html
	    }
	
	@GetMapping("/product/{productId}")
	public Product getProductById(@PathVariable Long productId, Model model) {
		// 獲取產品資料並添加到模型中
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		
		return productService.getProductById(productId);
	}
	
	@GetMapping("/product/{productId}/variants")  // 注意這裡的路徑不同
	@ResponseBody  // 確保返回 JSON 數據
	public List<ProductVariant> getVariantsByProductId(@PathVariable Long productId) {
		return productVariantService.getVariantsByProductId(productId);
	}
	
	// 商品上架頁面的顯示
	 @GetMapping("/products/add")
	    public String showProductAddPage(Model model) {
	        // 假設有CategoryService、TypeService等服務類別來處理數據獲取
	        model.addAttribute("categories", categoryService.getAllCategories());
	        model.addAttribute("types", typeService.getAllTypes());
	        model.addAttribute("sizes", sizeService.getAllSizes());
	        model.addAttribute("colors", colorService.getAllColors());
	        model.addAttribute("product", new Product());  // 這裡可以是新的Product實體類
	        return "productadd"; 
	    }


	
	
	
	
	
	
	
	
	//--------------------------------------------
	
	
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
	
	@GetMapping("/customer-support")
	public String customerSupport() {
		return "customerManagement";
	}


	
//page功能 測試
	 @GetMapping("/cmshome")  // 設定路由
	    public String showcmsHomePage() {
	        return "index";  
	    }
	
	
	@GetMapping("/loadPageContent")
	public String loadPageContent(@RequestParam String page) {
		 if ("productlist".equals(page)) {
	         return "fragments/productlist :: content"; // 返回指定頁面的 Thymeleaf 片段
	     } else if ("dashboard".equals(page)) {
	         return "fragments/dashboard :: content";
	     } else if("overview".equals(page)) {
	    	 return "fragments/overview :: overview";
	     } else if("users".equals(page)) {
	    	 return "fragments/users :: users";
	     } else if("products/add}".equals(page)) {
	    	 return "fragments/products/add} :: products/add}";
	     } else if("orders".equals(page)) {
	    	 return "fragments/orders :: orders";
	     } else if("pagemanagement".equals(page)) {
	    	 return "fragments/pagemanagement :: pagemanagement";
	     }
	     // 其他頁面邏輯
	     return "fragments/default :: content";
			}
		}
	
	
	

    

