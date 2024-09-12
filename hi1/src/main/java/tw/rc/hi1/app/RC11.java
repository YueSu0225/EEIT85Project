package tw.rc.hi1.app;

import java.io.FileOutputStream;

import tw.rc.h1.dao.MemberDao;
import tw.rc.h1.model.Member;

public class RC11 {
	//讀圖且存在dir2
	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		try {
			Member member = memberDao.getMemberById(15);
			System.out.println(member.getName());
			
			byte[] icon = member.getIcon();
			new Thread() {
				public void run() {
					try {
						FileOutputStream fout = new FileOutputStream("dir2/rc.png");
						fout.write(icon);
						fout.flush();
						fout.close();
						System.out.println("OK");
					}catch(Exception e) {
						System.out.println(e);
					}
				};
			}.start();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
