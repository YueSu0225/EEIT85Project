package tw.rc.hi1.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RC04 {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactoryV2().openSession()){

			String sql = "SELECT * FROM member";
			Transaction transaction = session.beginTransaction();
			
			Query query = session.createNativeQuery(sql);
			System.out.println(query.getResultCount());
			
			List<Object[]> results = query.getResultList();
			for (Object[] result : results) {
//				System.out.println(result.length);
				System.out.println(result[3]);
			}
			
			transaction.commit();
			
			
		}catch (Exception e) {
			System.out.println(e);
		}

	}

}
