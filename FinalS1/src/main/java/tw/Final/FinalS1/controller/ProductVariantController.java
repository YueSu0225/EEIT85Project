package tw.Final.FinalS1.controller;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.Final.FinalS1.model.Color;
import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.model.Size;
import tw.Final.FinalS1.service.ColorService;
import tw.Final.FinalS1.service.ProductService;
import tw.Final.FinalS1.service.ProductVariantService;
import tw.Final.FinalS1.service.SizeService;

@RestController
@RequestMapping("/product-variants")
public class ProductVariantController {
	
    @Autowired
    private ProductVariantService productVariantService;
    
    @Autowired
    private ColorService colorService;
    
    @Autowired
    private SizeService sizeService;
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductVariant> getAllProductVariants() {
        return productVariantService.getAllProductVariants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariant> getProductVariantById(@PathVariable Long id) {
        ProductVariant productVariant = productVariantService.getProductVariantById(id);
        if (productVariant != null) {
            return ResponseEntity.ok(productVariant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProductVariant> addProductVariant(@RequestBody Map<String, Object> requestData) {
        try {
           
            System.out.println("變體資料：" + requestData);

            Long productId = requestData.get("product_id") != null ? Long.valueOf(requestData.get("product_id").toString()) : null;
            Long colorId = requestData.get("color") != null ? Long.valueOf(requestData.get("color").toString()) : null;
            Long sizeId = requestData.get("size") != null ? Long.valueOf(requestData.get("size").toString()) : null;
            int price = requestData.get("price") != null ? Integer.parseInt(requestData.get("price").toString()) : 0;
            int stock = requestData.get("stock") != null ? Integer.parseInt(requestData.get("stock").toString()) : 0;
            String image1Base64 = (String) requestData.get("image1");
            String image2Base64 = (String) requestData.get("image2");
            String image3Base64 = (String) requestData.get("image3");
           

            
           

            
            if (image1Base64 != null && image1Base64.startsWith("data:image")) {
                image1Base64 = image1Base64.substring(image1Base64.indexOf(",") + 1);
            }
            if (image2Base64 != null && image2Base64.startsWith("data:image")) {
                image2Base64 = image2Base64.substring(image2Base64.indexOf(",") + 1);
            }
            if (image3Base64 != null && image3Base64.startsWith("data:image")) {
                image3Base64 = image3Base64.substring(image3Base64.indexOf(",") + 1);
            }

            
            Product product = productService.getProductById(productId);
            

            // 創建新的 ProductVariant
            ProductVariant variant = new ProductVariant();
            variant.setProduct(product);
            variant.setColor(colorService.getColorById(colorId));
            variant.setSize(sizeService.getSizeById(sizeId));
            variant.setPrice(price);
            variant.setStock(stock);
            variant.setImage(image1Base64);
            variant.setImage2(image2Base64);
            variant.setImage3(image3Base64);

            
            ProductVariant newVariant = productVariantService.addProductVariant(variant);
            return ResponseEntity.ok(newVariant);
        } catch (Exception e) {
            System.out.println("錯誤: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(@PathVariable Long id, @RequestBody Map<String, Object> variantDetails) {
    	ProductVariant existingVariant = productVariantService.getProductVariantById(id);
        if (existingVariant == null) {
            return ResponseEntity.notFound().build();
        }
        
        Long productId  = Long.valueOf(variantDetails.get("product_id").toString());
    	Long colorId = Long.valueOf(variantDetails.get("color_id").toString());
    	Long sizeId = Long.valueOf(variantDetails.get("size_id").toString());
	    int price = Integer.parseInt(variantDetails.get("price").toString());
	    int stock = Integer.parseInt(variantDetails.get("stock").toString());
	    String imageBase64 =(String) variantDetails.get("image");

        
        Product product = productService.getProductById(productId);
        Color color = colorService.getColorById(colorId);
        Size size = sizeService.getSizeById(sizeId);
        
        if (product == null || color == null || size == null) {
            return ResponseEntity.badRequest().body(null);
        }
        
        existingVariant.setProduct(product);
        existingVariant.setColor(color);
        existingVariant.setSize(size);
        existingVariant.setPrice(price);  
        existingVariant.setStock(stock);
        existingVariant.setImage(imageBase64);
	    existingVariant.setCreatedAt(new Timestamp(System.currentTimeMillis()));

	    ProductVariant updatedVariant = productVariantService.updateProductVariant(id, existingVariant);
	    
	    return ResponseEntity.ok(updatedVariant);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable Long id) {
    	productVariantService.deleteProductVariant(id);
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/product/{productId}")
    public List<ProductVariant> getVariantsByProductId(@PathVariable Long productId) {
    	return productVariantService.getVariantsByProductId(productId);
    }
    
    
    //用productid 找到該商品變體相對應庫存
    @GetMapping("/{productId}/variant")
    public ProductVariant getVariantByProductAndAttributes(
            @PathVariable Long productId,
            @RequestParam Long colorId,
            @RequestParam Long sizeId) {
        return productVariantService.getVariantByAttributes(productId, colorId, sizeId);
    }
}

        
        
        

