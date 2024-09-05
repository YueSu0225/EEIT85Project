package tw.RC.tutor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class RC42 {

	public static void main(String[] args) {
		String mesg = "Hello, 資展國際\n"; 
		try {
			FileOutputStream fout = new FileOutputStream("dir1/file3.txt", true);
			fout.write(mesg.getBytes());
			fout.flush();
			fout.close();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
