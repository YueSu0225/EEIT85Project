package tw.rc.service;

import org.springframework.web.multipart.MultipartFile;

import tw.rc.model.ResponseUser;
import tw.rc.model.Test;
import tw.rc.model.User;

//Service的介面  定義方法  沒有實作  所以要定義屬性同時初始化
public interface UserService {
	
	public ResponseUser regUser(User user);
	
	public ResponseUser isExistUser(String account);
	
	public ResponseUser loginUser(User user);

}
