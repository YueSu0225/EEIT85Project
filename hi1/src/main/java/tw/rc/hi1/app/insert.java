package tw.rc.hi1.app;

import java.util.List;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class insert {

	public void addMember(String string, String string2, String string3) {
		
		
	}
	
	public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
		 try {
	            // 先查詢所有成員以確認數據
	            List<Member> members = memberDao.getAll();            
	            for (Member member : members) {                
	                System.out.println("Before update: " + member.getName());
	            
	            }
        // 使用 Hibernate 實體對象進行插入
        Member newMember = new Member();
        newMember.setAccount("apple");
        newMember.setPasswd("123456");
        newMember.setName("APPLE");
        
        
        if (newMember != null) {
            memberDao.addMember(newMember);
            

            members = memberDao.getAll();
            for (Member member : members) {                
                System.out.println("After update: " + member.getName());
            }
        } else {
            System.out.println("false");
        }
} catch (Exception e) {
	System.out.println(e);
}

        
        
    }

}
