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
    public ResponseEntity<ProductVariant> addproductVariant(@RequestBody Map<String,Object> requestData ){
    	
    	Long productId  = Long.valueOf(requestData.get("product_id").toString());
    	Long colorId = Long.valueOf(requestData.get("color_id").toString());
    	Long sizeId = Long.valueOf(requestData.get("size_id").toString());
	    BigDecimal price = new BigDecimal(requestData.get("price").toString()); 
	    int stock = Integer.valueOf(requestData.get("stock").toString());
	    
	    Product product = productService.getProductById(productId);
	    Color color = colorService.getColorById(colorId);
	    Size size = sizeService.getSizeById(sizeId);
	    
	    if (product == null || color == null || size == null) {
	        return ResponseEntity.badRequest().body(null);
	    }
	    
	    ProductVariant newVariant = new ProductVariant();
	    newVariant.setProduct(product);
	    newVariant.setColor(color);
	    newVariant.setSize(size);
	    newVariant.setPrice(price);
	    newVariant.setStock(stock);
	    
	    ProductVariant savedVariant = productVariantService.addProductVariant(newVariant);

	    return ResponseEntity.ok(savedVariant);
    	
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(@PathVariable Long id, @RequestBody Map<String, Object> variantDetails) {
    	ProductVariant existingVariant = productVariantService.getProductVariantById(id);
        if (existingVariant == null) {
            return ResponseEntity.notFound().build();
        }
        
        Long productId = Long.valueOf(variantDetails.get("product_id").toString());
        Long colorId = Long.valueOf(variantDetails.get("color_id").toString());
        Long sizeId = Long.valueOf(variantDetails.get("size_id").toString());
	    BigDecimal price = new BigDecimal(variantDetails.get("price").toString()); 
        int stock = Integer.valueOf(variantDetails.get("stock").toString());
        
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

        
        ProductVariant updatedVariant = productVariantService.updateProductVariant(id, existingVariant);

        return ResponseEntity.ok(updatedVariant);
        
        
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable Long id) {
        productVariantService.deleteProductVariant(id);
        return ResponseEntity.noContent().build();
    }
}

