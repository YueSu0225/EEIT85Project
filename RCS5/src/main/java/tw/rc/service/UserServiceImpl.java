package tw.rc.service;

import java.util.List;

import org.mindrot.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.rc.model.ResponseUser;
import tw.rc.model.User;
import tw.rc.model.UserStatus;
import tw.rc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public ResponseUser regUser(User user) {
		ResponseUser responseUser = isExistUser(user.getAccount());
		if(responseUser.getUserStatus() == UserStatus.NOT_EXIST) {
			user.setPasswd(BCrypt.hashpw(user.getPasswd(), BCrypt.gensalt()));
		
			User newUser = userRepository.save(user);
			if (newUser.getId() != null ) {
				responseUser.setUserStatus(UserStatus.ADD_SUCCESS);
				responseUser.setMesg("新增成功");
				responseUser.setUser(newUser);
			}else {
				responseUser.setUserStatus(UserStatus.ADD_FAILURE);
				responseUser.setMesg("新增失敗");
				responseUser.setUser(user);
			}
			
			
		}else {
			responseUser.setMesg("帳號已存在(新增)");
		}		
		return responseUser;
	}

	@Override
	public ResponseUser isExistUser(String account) {
		ResponseUser responseUser = new ResponseUser();
		List<User> users = userRepository.findByAccount(account);
		if (users != null && users.size() > 0) {
			responseUser.setUserStatus(UserStatus.EXIST);
			responseUser.setMesg("帳號已存在(查詢)");
			responseUser.setUser(users.get(0));
		} else {
			responseUser.setUserStatus(UserStatus.NOT_EXIST);
			responseUser.setMesg("帳號不存在");
			responseUser.setUser(null);
		}
		
		return responseUser;
	}

	@Override
	public ResponseUser loginUser(User user) {
		ResponseUser responseUser = isExistUser(user.getAccount());
		if (responseUser.getUserStatus() == UserStatus.NOT_EXIST) {
			responseUser.setUserStatus(UserStatus.LOGIN_FAILURE);
			responseUser.setMesg("登入失敗(找不到帳號)");
			responseUser.setUser(user);
		}else {
			User userDB = responseUser.getUser();
			if (BCrypt.checkpw(user.getPasswd(), userDB.getPasswd())) {
				responseUser.setUserStatus(UserStatus.LOGIN_SUCCESS);
				responseUser.setMesg("登入成功");
				responseUser.setUser(userDB);
			}else {
				responseUser.setUserStatus(UserStatus.LOGIN_FAILURE);
				responseUser.setMesg("登入失敗(密碼不正確)");
				responseUser.setUser(user);
			}
		}
			
		return responseUser;
	}

	
	
}
