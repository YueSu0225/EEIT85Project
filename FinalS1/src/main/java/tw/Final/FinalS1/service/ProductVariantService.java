package tw.Final.FinalS1.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.repository.ProductRepository;
import tw.Final.FinalS1.repository.ProductVariantRepository;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;
    
	@Autowired 
	private ProductRepository productRepository;

    public List<ProductVariant> getAllProductVariants() {
        return productVariantRepository.findAll();
    }

    public ProductVariant getProductVariantById(Long id) {
        return productVariantRepository.findById(id).orElse(null);
    }
    

    public ProductVariant addProductVariant(ProductVariant productVariant) {
        // 檢查必須的欄位，確保變體關聯到產品
        if (productVariant.getProduct() == null || productVariant.getProduct().getId() == null) {
            throw new IllegalArgumentException("Product must be set for the variant and must have a valid ID.");
        }

        // 檢查 color 和 size 欄位是否正確設置
        if (productVariant.getColor() == null || productVariant.getColor().getId() == null) {
            throw new IllegalArgumentException("Color must be set for the variant and must have a valid ID.");
        }

        if (productVariant.getSize() == null || productVariant.getSize().getId() == null) {
            throw new IllegalArgumentException("Size must be set for the variant and must have a valid ID.");
        }

        // 檢查價格和庫存是否有效
        if (productVariant.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        }

        if (productVariant.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }

        // 處理圖片資料，如果圖片是 Base64 格式，移除前綴
        String imageBase64 = productVariant.getImage();
        if (imageBase64 != null && imageBase64.startsWith("data:image")) {
            imageBase64 = imageBase64.substring(imageBase64.indexOf(",") + 1);
            productVariant.setImage(imageBase64);
        }

        // 設定時間戳
        productVariant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productVariant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // 保存並返回變體
        return productVariantRepository.save(productVariant);
    }


    public ProductVariant updateProductVariant(Long id, ProductVariant productVariantDetails) {
        ProductVariant productVariant = productVariantRepository.findById(id).orElse(null);
        if (productVariant != null) {
            productVariant.setProduct(productVariantDetails.getProduct());
            productVariant.setColor(productVariantDetails.getColor());
            productVariant.setSize(productVariantDetails.getSize());
            productVariant.setPrice(productVariantDetails.getPrice());
            productVariant.setStock(productVariantDetails.getStock());
            productVariant.setImage(productVariantDetails.getImage());
            productVariant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return productVariantRepository.save(productVariant);
        }
        return null;
    }

    public void deleteProductVariant(Long id) {
        productVariantRepository.deleteById(id);
    }
    
    public List<ProductVariant> getVariantsByProductId(Long productId) {
        return productVariantRepository.findByProductId(productId);
    }
}

