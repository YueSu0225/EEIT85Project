package tw.rc.h1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import tw.rc.hi1.app.HibernateUtil;

public class UserBikeDao {
	private int id;
	private String name;
	private int bid;
	private int uid;
	private String color;
	private double speed;
	
	public UserBikeDao(int id, String name, int bid, int uid, String color, double speed) {
		this.id = id;
		this.name = name;
		this.bid = bid;
		this.uid = uid;
		this.color = color;
		this.speed = speed;
		
	}
	
	public static List<UserBikeDao> queryJoin(){
		String sql = "SELECT u.id id, u.name name, b.bid bid, b.uid uid, b.color color, b.speed speed " + 
				"FROM user u JOIN bike b ON u.id = b.uid ORDER BY id";		
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			NativeQuery<UserBikeDao> result = session.createNativeQuery(sql, UserBikeDao.class);
			return result.list();
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	public static List<UserBikeDao> queryJoinById(int id) {
		String sql = "SELECT u.id id, u.name name, b.bid bid, b.uid uid, b.color color, b.speed speed " + 
					"FROM user u JOIN bike b ON u.id = b.uid WHERE id = %d ORDER BY id";
		String sql2 = String.format(sql, id);
		System.out.println(sql2);
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			NativeQuery<UserBikeDao> result = session.createNativeQuery(sql2, UserBikeDao.class);
			return result.list();
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBid() {
		return bid;
	}

	public int getUid() {
		return uid;
	}

	public String getColor() {
		return color;
	}

	public double getSpeed() {
		return speed;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	
}
