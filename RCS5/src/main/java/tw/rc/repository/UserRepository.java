package tw.rc.repository;

import org.springframework.data.repository.CrudRepository;

import tw.rc.model.User;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long>{
	public List<User> findByAccount(String account);
}
