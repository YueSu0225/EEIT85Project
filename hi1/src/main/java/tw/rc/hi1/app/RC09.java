package tw.rc.hi1.app;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class RC09 {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		try {
			Member member = memberDao.getMemberById(8);
			System.out.println(member.getName());
			
			
			member.setAccount("RCC");
			
			memberDao.updateMember(member);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
