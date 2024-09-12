package tw.rc.hi1.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RC02 {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			System.out.println("OK, get session!!");
			
			String sql = "UPDATE member SET account = :account, passwd = :passwd, name = :name WHERE id = :id";
			
			Transaction transaction = session.beginTransaction();
			Query query = session.createNativeQuery(sql)
				.setParameter("account", "rc1")
				.setParameter("passwd", "123456")
				.setParameter("name", "RC")
				.setParameter("id", "1");
				
			int n = query.executeUpdate();
			
			transaction.commit();
			
			System.out.println(n);
		}catch (Exception e) {
			System.out.println(e);
		}

	}

}
