package tw.rc.hi1.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RC03 {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			System.out.println("OK, get session!!");
			
			String sql = "DELETE FROM member WHERE id = :id" ;
			
			Transaction transaction = session.beginTransaction();
			Query query = session.createNativeQuery(sql)

				.setParameter("id", "5");
				
			int n = query.executeUpdate();
			
			transaction.commit();
			
			System.out.println(n);
		}catch (Exception e) {
			System.out.println(e);
		}

	}

}
