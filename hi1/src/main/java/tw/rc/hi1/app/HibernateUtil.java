package tw.rc.hi1.app;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import tw.rc.h1.model.Member;

// static always here
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {//整份專案一份工廠,沒有我創,有就用原本的
			Configuration conf = new Configuration();//組汰檔路線
			conf.configure("hibernate.cfg.xml");
			
			conf.addAnnotatedClass(Member.class);
			
			registry = new StandardServiceRegistryBuilder()
						.applySettings(conf.getProperties())
						.build();
			sessionFactory = conf.buildSessionFactory(registry);			
		}
		
		return sessionFactory;
	}
	
}
