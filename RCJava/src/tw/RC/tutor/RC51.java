package tw.RC.tutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class RC51 {
	public static void main(String[] args) {
		RC513 obj = new RC513();
		
		try {
			ObjectOutputStream oout = new ObjectOutputStream(
					new FileOutputStream("dir1/test1.ok"));
			oout.writeObject(obj);
			oout.flush();
			oout.close();
			System.out.println("OK");
			System.out.println("----");
			
			ObjectInputStream oin = new ObjectInputStream(
					new FileInputStream("dir1/test1.ok"));
			oin.readObject();
			oin.close();
			System.out.println("OK2");	
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
class RC511 {
	RC511() {System.out.println("RC511()");}
}
class RC512 extends RC511 {
	RC512() {System.out.println("RC512()");}
}
class RC513 extends RC512 implements Serializable {//序列化:implements Serializable,父類別若沒序列化會直接幫你初始化
	RC513() {System.out.println("RC513()");}	//若父類別已經序列化了,子類別也會有序列化
}
