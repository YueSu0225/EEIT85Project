package tw.Final.FinalS1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.Final.FinalS1.model.CartItemsModel;
import tw.Final.FinalS1.model.CartModel;

	public interface CartItemsRepository extends  JpaRepository<CartItemsModel, Integer> {

	 List<CartItemsModel> findByCartId(Long cartId);
}
