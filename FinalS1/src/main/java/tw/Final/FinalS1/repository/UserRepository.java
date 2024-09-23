package tw.Final.FinalS1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
	boolean existsByAccount(String account);

	public List<UserModel> findByAccount(String account);
	
	public List<UserModel> findByGoogleId(String googleId);
	
	public UserModel findByUuid(String uuid);
}