package tw.rc.hi1.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class TTTEST {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		 try {
	            // 先查詢所有成員以確認數據
	            List<Member> members = memberDao.getAll();            
	            for (Member member : members) {                
	                System.out.println("Before update: " + member.getName());
	            
	            }
	            int IdNumber = 4;
	            
	            Member memberToUpdate = members.stream()
                        .filter(m -> {                               
                        	try {
                            return (m.getId()) == IdNumber;
                        } catch (NumberFormatException e) {
                            return false; // 如果 getId() 不是有效的整數，則忽略這個對象
                        }
                    })
                        .findFirst()
                        .orElse(null);


	            if (memberToUpdate != null) {
	                // 設置新的數據
	                memberToUpdate.setName("fuck");                
	                
	                // 更新資料
	                memberDao.updateMember(memberToUpdate);
	                
	                // 再次查詢以確認更新
	                members = memberDao.getAll();
	                for (Member member : members) {                
	                    System.out.println("After update: " + member.getName());
	                }
	            } else {
	                System.out.println("Member with ID"+ IdNumber +"not found.");
	            }
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
