package tw.rc.hi1.app;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.JdbcSettings;

import tw.rc.h1.model.Account;
import tw.rc.h1.model.Bike;
import tw.rc.h1.model.Cart;
import tw.rc.h1.model.Course;
import tw.rc.h1.model.Member;
import tw.rc.h1.model.MemberInfo;
import tw.rc.h1.model.Student;
import tw.rc.h1.model.User;

// static always here
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {//整份專案一份工廠,沒有我創,有就用原本的
			Configuration conf = new Configuration();//組汰檔路線
			conf.configure("hibernate.cfg.xml");
			
			conf.addAnnotatedClass(Member.class);
			conf.addAnnotatedClass(MemberInfo.class);
			conf.addAnnotatedClass(User.class);
			conf.addAnnotatedClass(Bike.class);
			
			conf.addAnnotatedClass(Account.class);
			conf.addAnnotatedClass(Cart.class);
			
			conf.addAnnotatedClass(Student.class);
			conf.addAnnotatedClass(Course.class);

			registry = new StandardServiceRegistryBuilder()
						.applySettings(conf.getProperties())
						.build();
			sessionFactory = conf.buildSessionFactory(registry);			
		}
		
		return sessionFactory;
	}
	//地2建立工廠的方法
	public static SessionFactory getSessionFactoryV2() {
		if (sessionFactory == null) {//整份專案一份工廠,沒有我創,有就用原本的
			Configuration conf = new Configuration();//組汰檔路線
			
			Properties prop = new Properties();
			prop.put(JdbcSettings.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
			prop.put(JdbcSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/ispan");
			prop.put(JdbcSettings.JAKARTA_JDBC_USER, "root");
			prop.put(JdbcSettings.JAKARTA_JDBC_PASSWORD, "root");
			
			prop.put(Environment.SHOW_SQL, "true");
			prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			
			conf.setProperties(prop);
			
			conf.addAnnotatedClass(Member.class);
			conf.addAnnotatedClass(MemberInfo.class);
			conf.addAnnotatedClass(User.class);
			conf.addAnnotatedClass(Bike.class);
			
			conf.addAnnotatedClass(Account.class);
			conf.addAnnotatedClass(Cart.class);

			conf.addAnnotatedClass(Student.class);
			conf.addAnnotatedClass(Course.class);
			
			registry = new StandardServiceRegistryBuilder()
						.applySettings(conf.getProperties())
						.build();
			sessionFactory = conf.buildSessionFactory(registry);			
		}
		
		return sessionFactory;
	}
}
