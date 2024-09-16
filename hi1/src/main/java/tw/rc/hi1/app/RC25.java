package tw.rc.hi1.app;

import tw.rc.h1.dao.StudentDao;
import tw.rc.h1.model.Student;

public class RC25 {

	public static void main(String[] args) {
		Student s1 = new Student("John");
		Student s2 = new Student("Mary");
		Student s3 = new Student("Kevin");
		Student s4 = new Student("Brad");
		
		StudentDao dao = new StudentDao();
		dao.add(s1);
		dao.add(s2);
		dao.add(s3);
		dao.add(s4);
	}

}
