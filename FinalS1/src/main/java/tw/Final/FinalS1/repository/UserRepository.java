package tw.Final.FinalS1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.Final.FinalS1.model.finalUserModel;


@Repository
public interface UserRepository extends JpaRepository<finalUserModel, Integer> {
	boolean existsByAccount(String account);

	public List<finalUserModel> findByAccount(String account);
}