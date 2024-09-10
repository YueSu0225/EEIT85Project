package tw.rc.h1.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.rc.h1.model.Course;
import tw.rc.h1.model.Student;
import tw.rc.hi1.app.HibernateUtil;

public class CourseDao {
	public void add(Course course) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();	
			session.persist(course);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
		}

	}
	public void update(Course course) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();	
			session.merge(course);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
		}

	}
	public List<Course> getAll() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query<Course> result = session.createQuery("FROM Course", Course.class);
			return result.getResultList();
		}catch (Exception e) {
			System.out.println(e);
			return new LinkedList<Course>();
		}

	}
	public Course getById(int id) {
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			return session.get(Course.class, id);
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}
