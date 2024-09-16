package tw.rc.hi1.app;

import tw.rc.h1.dao.CourseDao;
import tw.rc.h1.model.Course;

public class RC24 {

	public static void main(String[] args) {
		CourseDao dao = new CourseDao();
		
		Course course1 = new Course("PE");
		Course course2 = new Course("History");
		
		dao.add(course1);
		dao.add(course2);
		
	}

}
