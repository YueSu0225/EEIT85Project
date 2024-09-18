package tw.rc.hi1.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tw.rc.h1.model.Course;
import tw.rc.h1.model.Student;

public class RC23 {

	public static void main(String[] args) {
		
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			
			Student s1 = new Student("Tony");
			
			
			Course c1 = new Course("Computer");	

			
			s1.addCourse(c1);

			
			session.persist(s1);
			
			transaction.commit();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
