package tw.Final.FinalS1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.CartModel;
import tw.Final.FinalS1.model.UserModel;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {

	public CartModel findByUser(UserModel user);
		
}