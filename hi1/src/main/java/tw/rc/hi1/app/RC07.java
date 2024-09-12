package tw.rc.hi1.app;

import java.io.File;
import java.io.FileInputStream;
import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.BCrypt;
import tw.rc.h1.model.Member;

public class RC07 {
		//INSERT INTO + icon
	public static void main(String[] args) {
		Member member = new Member();
		member.setAccount("WuKong");
		member.setPasswd(BCrypt.hashpw("123456", BCrypt.gensalt()));
		member.setName("悟空");
		
		File file = new File("dir1/ball2.png");
		try {
			FileInputStream fin = new FileInputStream(file);
			byte[] icon = fin.readAllBytes();
			member.setIcon(icon);
		} catch (Exception e) {

			System.out.println(e);
		}
		
		
		MemberDao memberDao = new MemberDao();
		memberDao.addMember(member);
		
	}

}
