package tw.rc.h1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.rc.h1.model.Member;
import tw.rc.hi1.app.HibernateUtil;

public class MemberDao {
	
	public List<Member> getAll() throws Exception{
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			String hql = "FROM Member";
			//Transaction transaction = session.beginTransaction();
			Query<Member> query = session.createQuery(hql, Member.class);
			//transaction.commit();
			return query.getResultList();
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}
		
	}
	
	//自己做的
	public void updateMember(Member member) throws Exception{
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			
            transaction = session.beginTransaction();
            session.update(member);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
            throw new Exception();
        } 
    }
	//自己做的
    public void addMember(Member member) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(member); // 使用 session.save 來插入實體對象
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println(e);
            throw new Exception();
        }
    }



}
