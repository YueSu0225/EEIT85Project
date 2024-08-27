package tw.RC.tutor;

import tw.RC.apis.Bike;

public class RC16 {

	public static void main(String[] args) {
		String s1 = new String();
		System.out.println(s1.length());//0
		
		byte[] b1 = {97, 98, 99, 100};
		String s2 = new String(b1);
		System.out.println(s2.length());//4
		System.out.println(s2);//abcd
		
		Bike b2 = new Bike();
		System.out.println(b2);
		
		int c = 12;
		System.out.println(c);//12
		
		Object obj1 = new Object();
		System.out.println(obj1);//java.lang.Object@6fdb1f78
		
		String s3 = "RC";//物件實體1,創建"RC"字串物件
		System.out.println(s3);//RC
		String s4 = "RC";//物件實體1,使用s3創建的字串物件
		String s5 = new String("RC");//物件實體2,創建新字串物件
		String s6 = new String("RC");//物件實體3,創建新字串物件
		System.out.println(s3 == s4);//true
		System.out.println(s3 == s5);//false
		System.out.println(s6 == s5);//false
		System.out.println(s6.equals(s3));//true
		
		System.out.println("RC".charAt(0));
		
		System.out.println(s3.concat("Su"));
		System.out.println(s3);
	}

}
