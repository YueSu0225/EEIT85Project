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

	
	 // 根據分類和版型查詢商品
    List<Product> findByCategoryIdAndTypeId(Long categoryId, Long typeId);

    // 只根據分類查詢商品
    List<Product> findByCategoryId(Long categoryId);

    // 只根據版型查詢商品
    List<Product> findByTypeId(Long typeId);

	Optional<Product> findByName(String name);  // 返回 Optional 以便於處理空值

	Page<Product> findAll(Pageable pageable);
	


	
	

}
