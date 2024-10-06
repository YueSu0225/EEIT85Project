package tw.Final.FinalS1.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.repository.ProductRepository;
import tw.Final.FinalS1.repository.ProductVariantRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	

	public Product findByName(String name) {
		return productRepository.findByName(name).orElse(null); // 如果沒有找到，返回 null
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	 public Page<Product> getAllProducts(Pageable pageable) {
	        return productRepository.findAll(pageable);
	    }

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public Product addProduct(Product product) {
		product.setImage(product.getImage());
		product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product productDetails) {
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {
			product.setName(productDetails.getName());
			product.setDescription(productDetails.getDescription());
			product.setCategory(productDetails.getCategory());
			product.setType(productDetails.getType());
			product.setPrice(productDetails.getPrice());
			product.setImage(productDetails.getImage());
			product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			return productRepository.save(product);
		}
		return null;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public List<Product> getProductsByCategory(Long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	public List<Product> getProductsByType(Long typeId) {
		return productRepository.findByTypeId(typeId);
	}

	public List<Product> findByCategoryAndType(Long categoryId, Long typeId) {
		return productRepository.findByCategoryIdAndTypeId(categoryId, typeId);
	}

	public List<Product> findByFilters(Long categoryId, Long typeId) {
		if (categoryId != null && typeId != null) {
			return productRepository.findByCategoryIdAndTypeId(categoryId, typeId);
		} else if (categoryId != null) {
			return productRepository.findByCategoryId(categoryId);
		} else if (typeId != null) {
			return productRepository.findByTypeId(typeId);
		} else {
			return productRepository.findAll(); // 如果都沒有篩選條件，則返回所有商品
		}
	}

	// 使用 Optional 處理檢查產品是否存在
	public Optional<Product> checkProductByName(String productName) {
		return productRepository.findByName(productName);
	}

	// 查詢名稱包含關鍵字的商品
    public List<Product> searchProductsByName(String key) {
        if (key == null || key.isEmpty()) {
            return productRepository.findAll();  // 如果沒有搜尋關鍵字，返回所有商品
        } else {
            return productRepository.findByNameContainingIgnoreCase(key);  // 根據名稱模糊查詢
        }
    }
    
    
}
