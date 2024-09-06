package tw.rc.h1.dao;

import java.util.EmptyStackException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.rc.h1.model.Member;
import tw.rc.hi1.app.HibernateUtil;

public class MemberDao {
	
	//add member
	public void addMember(Member member) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.persist(member);//新版的save(( insert into
			
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
		}
	} 
	
	// fetch member by id
	public Member getMemberById(int id) throws Exception{
		try(Session session = HibernateUtil.getSessionFactory().openSession()){

			Member member = session.get(Member.class, id);
			return member;
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}
	}
	
	public void deleteMember(Member member) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.remove(member);//新版DELETE((砍掉
			
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
		}
	} 
	// 修改
	public void updateMember(Member member) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.merge(member);//新版DELETE((砍掉
			
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
		}
	} 
	
	public List<Member> getByWho(String hql) throws Exception{
		try (Session session = HibernateUtil.getSessionFactory().openSession()){

			Query<Member> query = session.createQuery(hql, Member.class);
			return query.getResultList();
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}
		
	}
	
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
//	public void updateMember(Member member) throws Exception{
//		Transaction transaction = null;
//		try (Session session = HibernateUtil.getSessionFactory().openSession()){
//			
//            transaction = session.beginTransaction();
//            session.update(member);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            System.out.println(e);
//            throw new Exception();
//        } 
//    }
	//自己做的
//    public void addMember(Member member) throws Exception{
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            session.save(member); // 使用 session.save 來插入實體對象((舊版
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            System.out.println(e);
//            throw new Exception();
//        }
//    }



}
