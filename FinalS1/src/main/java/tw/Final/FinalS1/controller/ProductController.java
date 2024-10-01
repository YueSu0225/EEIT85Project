package tw.Final.FinalS1.controller;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.Final.FinalS1.model.Category;
import tw.Final.FinalS1.model.Color;
import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.model.Size;
import tw.Final.FinalS1.model.Type;
import tw.Final.FinalS1.repository.ProductRepository;
import tw.Final.FinalS1.service.CategoryService;
import tw.Final.FinalS1.service.ColorService;
import tw.Final.FinalS1.service.ProductService;
import tw.Final.FinalS1.service.ProductVariantService;
import tw.Final.FinalS1.service.SizeService;
import tw.Final.FinalS1.service.TypeService;

@RestController
@RequestMapping("/products")
public class ProductController {                                                      

	                                                                           
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private SizeService sizeService;
	
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private ProductVariantService productVariantService;
	
	
	
	
	
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	 @GetMapping("/page")
	    public Page<Product> getPagedProducts(Pageable pageable) {
	        return productService.getpageProducts(pageable);
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
	public ResponseEntity<?> addProduct(@RequestBody Map<String, Object> requestData) {
	    try {
	        // 獲取並驗證產品基本信息
	        String name = requestData.get("name") != null ? requestData.get("name").toString() : null;
	        String description = requestData.get("description") != null ? requestData.get("description").toString() : null;
	        Long categoryId = requestData.get("category") != null ? Long.valueOf(requestData.get("category").toString()) : null;
	        Long typeId = requestData.get("type") != null ? Long.valueOf(requestData.get("type").toString()) : null;
	        int price = requestData.get("price") != null ? Integer.parseInt(requestData.get("price").toString()) : 0;

	        // 獲取三張圖片的 Base64 資料
	        String image1Base64 = requestData.get("image1") != null ? requestData.get("image1").toString() : null;
	        String image2Base64 = requestData.get("image2") != null ? requestData.get("image2").toString() : null;
	        String image3Base64 = requestData.get("image3") != null ? requestData.get("image3").toString() : null;

	        // 驗證分類和類型是否存在
	        Category category = categoryService.getCategoryById(categoryId);
	        Type type = typeService.getTypeById(typeId);

	        if (category == null || type == null) {
	            return ResponseEntity.badRequest().body("無效的分類或類型 ID");
	        }

	        // 檢查商品是否已經存在
	        Product existingProduct = productService.findByName(name);

	        Product product;
	        if (existingProduct != null) {
	            // 商品已存在，不更新 product 表中的圖片
	            product = existingProduct;
	            System.out.println("商品已存在，使用現有的產品ID: " + product.getId());
	        } else {
	            // 商品不存在，創建新商品
	            product = new Product();
	            product.setName(name);
	            product.setDescription(description);
	            product.setCategory(category);
	            product.setType(type);
	            product.setPrice(price);

	            // 如果商品第一次上架，將第一張圖片設置為主圖
	            if (image1Base64 != null && image1Base64.startsWith("data:image")) {
	                image1Base64 = image1Base64.substring(image1Base64.indexOf(",") + 1);
	                product.setImage(image1Base64);  // 設置主圖片
	            }

	            product.setCreatedAt(new Timestamp(System.currentTimeMillis()));

	            // 保存新產品
	            Product newProduct = productService.addProduct(product);
	            product = newProduct;
	            System.out.println("新創建的產品ID: " + product.getId());
	        }

	        // 檢查是否生成產品 ID
	        if (product.getId() == null) {
	            throw new Exception("產品ID生成失敗");
	        }

	        // 處理變體數據（假設變體數據在 requestData 的 'variants' 字段）
	        List<Map<String, Object>> variantsData = (List<Map<String, Object>>) requestData.get("variants");
	        if (variantsData != null) {
	            addProductVariants(product, variantsData); // 調用新增變體的私有方法
	        }

	        // 成功返回產品資料（包括變體）
	        return ResponseEntity.ok(product);

	    } catch (IllegalArgumentException e) {
	        System.out.println("數據驗證錯誤: " + e.getMessage());
	        return ResponseEntity.badRequest().body("數據驗證錯誤：" + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("處理失敗: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("處理失敗: " + e.getMessage());
	    }
	}

	private void addProductVariants(Product product, List<Map<String, Object>> variantsData) throws Exception {
	    for (Map<String, Object> variantData : variantsData) {
	        // 提取變體數據
	        Long colorId = variantData.get("color") != null ? Long.valueOf(variantData.get("color").toString()) : null;
	        Long sizeId = variantData.get("size") != null ? Long.valueOf(variantData.get("size").toString()) : null;
	        int variantPrice = variantData.get("price") != null ? Integer.parseInt(variantData.get("price").toString()) : 0;
	        int stock = variantData.get("stock") != null ? Integer.parseInt(variantData.get("stock").toString()) : 0;
	        String variantImage1Base64 = variantData.get("image1") != null ? variantData.get("image1").toString() : null;
	        String variantImage2Base64 = variantData.get("image2") != null ? variantData.get("image2").toString() : null;
	        String variantImage3Base64 = variantData.get("image3") != null ? variantData.get("image3").toString() : null;

	        // 去掉 Base64 的前綴
	        if (variantImage1Base64 != null && variantImage1Base64.startsWith("data:image")) {
	            variantImage1Base64 = variantImage1Base64.substring(variantImage1Base64.indexOf(",") + 1);
	        }
	        if (variantImage2Base64 != null && variantImage2Base64.startsWith("data:image")) {
	            variantImage2Base64 = variantImage2Base64.substring(variantImage2Base64.indexOf(",") + 1);
	        }
	        if (variantImage3Base64 != null && variantImage3Base64.startsWith("data:image")) {
	            variantImage3Base64 = variantImage3Base64.substring(variantImage3Base64.indexOf(",") + 1);
	        }

	        // 查找顏色和尺寸
	        Color color = colorService.getColorById(colorId);
	        Size size = sizeService.getSizeById(sizeId);

	        if (color == null || size == null) {
	            throw new IllegalArgumentException("無效的顏色或尺寸 ID");
	        }

	        // 創建並保存變體
	        ProductVariant variant = new ProductVariant();
	        variant.setProduct(product);  // 設置變體所屬的產品
	        variant.setColor(color);
	        variant.setSize(size);
	        variant.setPrice(variantPrice);
	        variant.setStock(stock);
	        variant.setImage(variantImage1Base64);  // 第一張圖片作為變體的主圖片
	        variant.setImage2(variantImage2Base64);  // 第二張圖片
	        variant.setImage3(variantImage3Base64);  // 第三張圖片
	        variant.setCreatedAt(new Timestamp(System.currentTimeMillis()));

	        productVariantService.addProductVariant(variant);
	    }
	}

	
	


	






	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Map<String,Object> productDetails){
		
		
		 
        String name = (String) productDetails.get("name");
        String description = (String) productDetails.get("description");
        Long categoryId = Long.valueOf(productDetails.get("category_id").toString());
        Long typeId = Long.valueOf(productDetails.get("type_id").toString());
        int price = Integer.parseInt(productDetails.get("price").toString());
        String imageBase64 = (String) productDetails.get("image");
		
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
	   product.setImage(imageBase64);
       product.setCreatedAt(new Timestamp(System.currentTimeMillis()));

	   
	   Product updatedProduct = productService.updateProduct(id, product);
	   return ResponseEntity.ok(updatedProduct);
		
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	}
	
	@GetMapping("products/category/{categoryId}")
	public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
	    return productService.getProductsByCategory(categoryId);
	}
	
	@GetMapping("/check")
	public ResponseEntity<Product> checkProductName(@RequestParam String name) {
	    Optional<Product> productOpt = productService.checkProductByName(name);
	    
	    if (productOpt.isPresent()) {
	        return ResponseEntity.ok(productOpt.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("products/type/{typeId}")
	public List<Product> getProductsByType(@PathVariable Long typeId) {
	    return productService.getProductsByType(typeId);
	}
	
	@GetMapping("/products/category/{categoryId}/type/{typeId}")
	public ResponseEntity<List<Product>> getProductsByCategoryAndType(
	        @PathVariable Long categoryId,
	        @PathVariable Long typeId) {
	    List<Product> products = productService.findByCategoryAndType(categoryId, typeId);
	    return ResponseEntity.ok(products);
	}


	
	 
	



}
