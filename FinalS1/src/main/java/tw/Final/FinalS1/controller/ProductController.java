package tw.Final.FinalS1.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.Final.FinalS1.model.Category;
import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.model.Type;
import tw.Final.FinalS1.service.CategoryService;
import tw.Final.FinalS1.service.ProductService;
import tw.Final.FinalS1.service.TypeService;

@RestController
@RequestMapping("/products")
public class ProductController {

	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	@Autowired
	private TypeService typeService;
	
	
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	    Product product = productService.getProductById(id);
	    if (product != null) {
	            return ResponseEntity.ok(product);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	}
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Map<String, Object> requestData) {
	    // 獲取請求中的數據
	    String name = (String) requestData.get("name");
	    String description = (String) requestData.get("description");
	    Long categoryId = Long.valueOf(requestData.get("category_id").toString());
	    Long typeId = Long.valueOf(requestData.get("type_id").toString());         
	    BigDecimal price = new BigDecimal(requestData.get("price").toString()); 
	    
	    
	    
	    
	    Category category = categoryService.getCategoryById(categoryId);
	    Type type = typeService.getTypeById(typeId);

	    if (category == null || type == null) {
	        return ResponseEntity.badRequest().body(null);  
	    }

	   
	    Product product = new Product();
	    product.setName(name);
	    product.setDescription(description);
	    product.setCategory(category); 
	    product.setType(type);          
	    product.setPrice(price);

	   
	    Product newProduct = productService.addProduct(product);
	    return ResponseEntity.ok(newProduct);
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Map<String,Object> productDetails){
		
		
		String name = (String) productDetails.get("name");
		String description = (String) productDetails.get("description");
		Long categoryId = Long.valueOf(productDetails.get("category_id").toString());
		Long typeId = Long.valueOf(productDetails.get("type_id").toString());
	    BigDecimal price = new BigDecimal(productDetails.get("price").toString()); 
		
		Category category = categoryService.getCategoryById(categoryId);
	    Type type = typeService.getTypeById(typeId);
	    if (category == null || type == null) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	   Product product = productService.getProductById(id);
	   
	   product.setName(name);
	   product.setDescription(description);
	   product.setCategory(category);  
	   product.setType(type);          
	   product.setPrice(price);
	   
	   Product updatedProduct = productService.updateProduct(id, product);
	   return ResponseEntity.ok(updatedProduct);
		
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	}
}
