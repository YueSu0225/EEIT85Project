package tw.rc.hi1.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RC01 {

	public static void main(String[] args) {
		System.out.println("Hello");
		
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		Session session = factory.openSession();
//		session.close();
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			System.out.println("OK, get session!!");
			
			String sql = "INSERT INTO member (account, passwd, name) values (:account, :passwd, :name)";
			
			Transaction transaction = session.beginTransaction();
			Query query = session.createNativeQuery(sql)
				.setParameter("account", "test5")
				.setParameter("passwd", "123456")
				.setParameter("name", "Test5");			
			int n = query.executeUpdate();
			
			transaction.commit();
			
			System.out.println(n);
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
