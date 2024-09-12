package tw.rc.hi1.app;

import java.util.LinkedList;
import java.util.List;

import tw.rc.h1.dao.UserBikeDao;
import tw.rc.h1.dao.UserDao;
import tw.rc.h1.model.Bike;
import tw.rc.h1.model.User;

public class RC22 {

		public static void main(String[] args) {
			try {
				List<UserBikeDao> ubikes = UserBikeDao.queryJoinById(4);
				User user = new UserDao().queryById(ubikes.getFirst().getId());
				for (Bike bike: user.getBikes()) {
					System.out.println("bike:" + bike.getBid());
				}
				
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}

}
