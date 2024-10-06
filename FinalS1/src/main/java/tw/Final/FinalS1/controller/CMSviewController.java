package tw.Final.FinalS1.controller;

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

import tw.Final.FinalS1.dto.RegisterRequest;
import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.service.CMSuserService;
import tw.Final.FinalS1.service.ProductService;
import tw.Final.FinalS1.service.ProductVariantService;

@Controller
public class CMSviewController {
	
	@Autowired
	private CMSuserService cmSuserService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductVariantService productVariantService;
	
	

	//這是信箱驗證碼的信件內容((與後臺無關,勿動
	@RequestMapping("/emailCode")
	public String emailCode() {
		return "emailCode";
	}
	
	@RequestMapping("/backed")
	public String backed() {
		return "backed";
	}
	
	
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
    
    
 // 產品清單頁面
    @RequestMapping("/productlist")
    public String productlist(@RequestParam(value = "key", required = false) String key, Model model) {
        List<Product> products = productService.searchProductsByName(key);  // 查詢產品
        model.addAttribute("products", products);  // 將查詢結果放入模型
        return "productlist";
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
    	


}	
	

