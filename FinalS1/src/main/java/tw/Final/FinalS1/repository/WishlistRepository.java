package tw.Final.FinalS1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tw.Final.FinalS1.model.WishlistModel;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistModel, Long> {
	
}