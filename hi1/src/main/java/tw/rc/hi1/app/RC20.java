package tw.rc.hi1.app;

import tw.rc.h1.dao.UserDao;
import tw.rc.h1.model.Bike;
import tw.rc.h1.model.User;

public class RC20 {

	public static void main(String[] args) {
		User user = new User();
		user.setName("RC4");
		
		Bike b1 = new Bike();
		b1.setColor("Color41");
		b1.setSpeed(1.2);
		b1.setUser(user);
		
		Bike b2 = new Bike();
		b2.setColor("Color42");
		b2.setSpeed(1.2);
		b2.setUser(user);
		
		Bike b3 = new Bike();
		b3.setColor("Color43");
		b3.setSpeed(1.2);
		b3.setUser(user);
		
		
		user.addBike(b1);
		user.addBike(b2);
		user.addBike(b3);
		
		UserDao userDao = new UserDao();
		userDao.save(user);
		
		
		
		
	}

}
