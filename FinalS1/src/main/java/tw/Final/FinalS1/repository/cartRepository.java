package tw.Final.FinalS1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.cartModel;

// 工廠 sql
@Repository
public interface cartRepository extends JpaRepository<cartModel, Integer> {
    
}