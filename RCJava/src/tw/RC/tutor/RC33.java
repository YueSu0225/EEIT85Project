package tw.RC.tutor;

import java.io.File;

public class RC33 {

	public static void main(String[] args) {
		File f1 = new File("d:/dir2");
		File f2 = new File("d:/test1");
		if (f1.exists()) {
			System.out.println("OK");
		}else {
			System.out.println("XX");
			if (f1.mkdir()) {
				System.out.println("ok2");
			}else {
				System.out.println("XX2");
			}
		}
		
		File nowDir = new File(".");
		System.out.println(nowDir.getAbsolutePath());
		
		
	}

}
