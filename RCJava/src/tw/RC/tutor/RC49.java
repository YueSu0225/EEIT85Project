package tw.RC.tutor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import tw.RC.apis.Student;

public class RC49 {
	public static void main(String[] args) {
		Student s1 = new Student("RC", 40, 72, 53);
		System.out.println(s1);
		System.out.println(s1.sum());
		System.out.println(s1.avg());
		s1.getBike().upSpeed();s1.getBike().upSpeed();s1.getBike().upSpeed();s1.getBike().upSpeed();
		System.out.println(s1.getBike().getSpeed());
		
		try {
			FileOutputStream fout = new FileOutputStream("dir1/rc.score");//產生檔案rc.score
			ObjectOutputStream oout = new ObjectOutputStream(fout);//序列化
			
			oout.writeObject(s1);//將s1寫進去oout檔案
			
			oout.flush();
			oout.close();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
