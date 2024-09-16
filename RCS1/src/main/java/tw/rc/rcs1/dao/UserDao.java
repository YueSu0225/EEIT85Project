package tw.rc.rcs1.dao;

import java.util.List;
import java.util.function.LongFunction;

import tw.rc.rcs1.model.User;

public interface UserDao {
	public User add(User user);
	
	public void update(User user);
	public void update(User user, Long id);
	
	public void delete(User user);
	public void delete(User user, Long id);

	public List<User> getAll();
	public User getById(Long id);
	public User getByAccount(String account);
}
