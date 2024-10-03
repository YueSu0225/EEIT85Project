package tw.Final.FinalS1.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import tw.Final.FinalS1.model.UserModel;
import tw.Final.FinalS1.model.WishlistModel;

public interface WishlistRepository extends JpaRepository<WishlistModel, Long>{
	
	public WishlistModel findByUser(UserModel user);

}
