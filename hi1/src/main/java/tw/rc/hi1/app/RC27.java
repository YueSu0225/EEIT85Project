package tw.rc.hi1.app;

import java.util.Set;

import tw.rc.h1.dao.CourseDao;
import tw.rc.h1.model.Course;
import tw.rc.h1.model.Student;

public class RC27 {

	public static void main(String[] args) {
		CourseDao dao = new CourseDao();
		Course course = dao.getById(4);
		Set<Student> students = course.getStudents();
		
		System.out.println(students.size());
		
		for (Student student : students) {
			System.out.println(student.getName());
		}
		
	}

}
