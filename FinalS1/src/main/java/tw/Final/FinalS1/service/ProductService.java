package tw.Final.FinalS1.service;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.Final.FinalS1.model.Product;
import tw.Final.FinalS1.repository.ProductRepository;

@Service
public class ProductService  {
	
	@Autowired 
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
	
	public Product addProduct(Product product) {
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

}
