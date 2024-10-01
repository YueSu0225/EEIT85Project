package tw.Final.FinalS1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {	

	public List<UserModel> findByAccount(String account);
	
	public List<UserModel> findByGoogleId(String googleId);
	
	public UserModel findByUuid(String uuid);
	    
    public Page<UserModel> findAll(Pageable pageable); 
    
    @Query("SELECT u FROM UserModel u WHERE " +
            "u.account LIKE CONCAT('%', :keyword, '%') OR " +
            "u.email LIKE CONCAT('%', :keyword, '%') OR " +
            "u.googleId LIKE CONCAT('%', :keyword, '%') OR " +
            "u.userInfoMedel.name LIKE CONCAT('%', :keyword, '%') OR " +
            "u.userInfoMedel.phone_number LIKE CONCAT('%', :keyword, '%') OR " +
            "u.userInfoMedel.address LIKE CONCAT('%', :keyword, '%') OR " +
            "u.userInfoMedel.birthday LIKE CONCAT('%', :keyword, '%')")
     public Page<UserModel> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}