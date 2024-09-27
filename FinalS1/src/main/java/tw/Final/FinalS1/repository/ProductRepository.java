package tw.Final.FinalS1.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tw.Final.FinalS1.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p FROM Product p LEFT JOIN FETCH p.productVariants WHERE p.category.id = :categoryId")
	List<Product> findByCategoryId(Long categoryId);
	
	Optional<Product> findByName(String name);  // 返回 Optional 以便於處理空值

	Page<Product> findAll(Pageable pageable);

}
