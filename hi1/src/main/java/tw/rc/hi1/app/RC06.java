package tw.rc.hi1.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class RC06 {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		try {
			List<Member> members = memberDao.getAll();			
			for (Member member : members) {				
				System.out.println(member.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
