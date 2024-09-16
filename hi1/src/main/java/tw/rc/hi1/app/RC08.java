package tw.rc.hi1.app;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class RC08 {
	//DELETE member
	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		try {
			Member member = memberDao.getMemberById(12);
			System.out.println(member.getName());
			memberDao.deleteMember(member);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
