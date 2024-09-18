package tw.rc.rcs1.service;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tw.rc.rcs1.dao.UserDao;
import tw.rc.rcs1.model.User;
import tw.rc.rcs1.model.UserReturn;
import tw.rc.rcs1.model.UserV2;
import tw.rc.rcs1.repository.UserRepository;

@Component
public class UserServiceImpl implements UserSevice{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserReturn addUser(User user) {
		//進去前先加密
		user.setPasswd(BCrypt.hashpw(user.getPasswd(), BCrypt.gensalt()));
		User newUser = userDao.add(user);
		
		//回傳
		UserReturn userReturn = new UserReturn();
		
		if (newUser != null) {
			userReturn.setMesg("OK");
			userReturn.setId(user.getId());
			userReturn.setAccount(user.getAccount());
			userReturn.setName(user.getName());
		}else {
			userReturn.setMesg("ERROK");
		}
		return userReturn;
	}

	@Override
	public User getUser(Long id) {
		return userDao.getById(id);
	}

	@Override
	public UserV2 addUser(UserV2 user) {
		user.setPasswd(BCrypt.hashpw(user.getPasswd(), BCrypt.gensalt()));
		UserV2 user2 = userRepository.save(user);
		return user2;
	}
	


}
