package tw.Final.FinalS1.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.ProductVariant;
import tw.Final.FinalS1.repository.ProductVariantRepository;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    public List<ProductVariant> getAllProductVariants() {
        return productVariantRepository.findAll();
    }

    public ProductVariant getProductVariantById(Long id) {
        return productVariantRepository.findById(id).orElse(null);
    }

    public ProductVariant addProductVariant(ProductVariant productVariant) {
        productVariant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productVariant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
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
}

