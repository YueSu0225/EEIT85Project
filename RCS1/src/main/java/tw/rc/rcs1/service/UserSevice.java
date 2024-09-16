package tw.rc.rcs1.service;

import tw.rc.rcs1.model.Hotel;
import tw.rc.rcs1.model.User;
import tw.rc.rcs1.model.UserReturn;
import tw.rc.rcs1.model.UserV2;

public interface UserSevice {
	public UserReturn addUser(User user);
	public User getUser(Long id);
	public UserV2 addUser(UserV2 user);
	
}
