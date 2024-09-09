package tw.rc.h1.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tw.rc.h1.model.Account;
import tw.rc.h1.model.Cart;
import tw.rc.hi1.app.HibernateUtil;

public class AccountCartDao {
	public void addAccount(Account account) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){			
			transaction = session.beginTransaction();
			session.persist(account);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	public void updateAccount(Account account) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){			
			transaction = session.beginTransaction();
			session.merge(account);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	public void removeAccount(Account account) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){			
			transaction = session.beginTransaction();
//			if(account.getCart() != null) {
//			session.remove(account.getCart());
//			
//			}
			session.remove(account);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public Account queryByAccountId(int id) throws Exception {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){			
			Account account = session.get(Account.class, id);
			return account;
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}
	}
	
	public Cart queryCartByCartId(int cid) throws Exception {
		try(Session session = HibernateUtil.getSessionFactory().openSession()){			
			Cart cart = session.get(Cart.class, cid);
			return cart;
		}catch (Exception e) {
			System.out.println(e);
			throw new Exception();
		}
	}

	
	public void removeCart(Cart cart) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()){			
			transaction = session.beginTransaction();
			session.remove(cart);
			transaction.commit();			
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
}
