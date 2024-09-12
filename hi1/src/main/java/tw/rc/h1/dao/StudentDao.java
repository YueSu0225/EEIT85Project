package tw.rc.h1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.rc.h1.model.Course;
import tw.rc.h1.model.Student;
import tw.rc.hi1.app.HibernateUtil;

public class StudentDao {
	public void add(Student student) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();	
			session.persist(student);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
		}

	}
	public void update(Student student) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();	
			session.merge(student);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
		}

	}
	public List<Student> getAll() {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query<Student> result = session.createQuery("FROM Student", Student.class);
			return result.getResultList();
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	public Student getById(int id) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.get(Student.class, id);
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}
