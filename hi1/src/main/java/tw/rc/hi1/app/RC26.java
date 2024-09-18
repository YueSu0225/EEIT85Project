package tw.rc.hi1.app;

import tw.rc.h1.dao.CourseDao;
import tw.rc.h1.dao.StudentDao;
import tw.rc.h1.model.Course;
import tw.rc.h1.model.Student;

public class RC26 {

	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		CourseDao courseDao = new CourseDao();
		Student student = dao.getById(3);
		if (student != null) {
			Course c4 = courseDao.getById(4);
			Course c5 = courseDao.getById(5);
			Course c6 = courseDao.getById(6);
			
			student.addCourse(c4);
			student.addCourse(c5);
			student.addCourse(c6);
			
			dao.update(student);
			
			
		}else {
			System.out.println("student not found");
		}
		
	}

}
