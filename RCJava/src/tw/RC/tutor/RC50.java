package tw.RC.tutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

import tw.RC.apis.Student;

public class RC50 {

	public static void main(String[] args) {
		
		try {
			FileInputStream fin = new FileInputStream("dir1/rc.score");//讀取檔案
			ObjectInputStream oin = new ObjectInputStream(fin);//解序列化
			
			Object obj = oin.readObject();//物件obj=可讀
			Student s1 = (Student)obj; //轉型 
			System.out.println(s1);
			System.out.println(s1.sum());
			System.out.println(s1.avg());
			
			oin.close();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
