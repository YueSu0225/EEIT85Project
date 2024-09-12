package tw.rc.hi1.app;

import java.util.List;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class RC10 {

	public static void main(String[] args) {
		
		String hql = "From Member member WHERE member.name LIKE '%文%'";
		MemberDao memberDao = new MemberDao();
		try {
			List<Member> members = memberDao.getByWho(hql);
//			System.out.println(members.size());
			for (Member member : members) {
				System.out.println(member.getAccount() + ":" + member.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
