package tw.Final.FinalS1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.CartModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {
		Optional<CartModel> findByUserId(Long userId);
}