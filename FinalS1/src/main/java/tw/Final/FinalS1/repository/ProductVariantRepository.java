package tw.Final.FinalS1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.Final.FinalS1.model.ProductVariant;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
	List<ProductVariant> findByProductId(Long productId);
	
    ProductVariant findByProductIdAndColorIdAndSizeId(Long productId, Long colorId, Long sizeId);

}

