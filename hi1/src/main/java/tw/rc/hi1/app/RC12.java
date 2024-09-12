package tw.rc.hi1.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tw.rc.h1.model.Member;
import tw.rc.h1.model.MemberInfo;

public class RC12 {

	public static void main(String[] args) {
		Member member = new Member();
		member.setAccount("test1");
		member.setPasswd("123456");
		member.setName("Test1");
		
		
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setAddr("aaaa");
		memberInfo.setPhone("123456");
		memberInfo.setBirthday("1999-01-02");
		
		member.setMemberInfo(memberInfo);
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.persist(member);//新版的save(( insert into
			
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
