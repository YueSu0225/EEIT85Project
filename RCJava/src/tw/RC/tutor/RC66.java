package tw.RC.tutor;

import org.mindrot.BCrypt;

public class RC66 {

	public static void main(String[] args) {
		String passwd = "123456";
		String newpasswd = BCrypt.hashpw(passwd, BCrypt.gensalt());
		System.out.println(newpasswd);
		System.out.println(newpasswd.length());
		if (BCrypt.checkpw("123456", newpasswd)) {
			System.out.println("OK");
		}else {
			System.out.println("XXX");
		}
		
	}

}
