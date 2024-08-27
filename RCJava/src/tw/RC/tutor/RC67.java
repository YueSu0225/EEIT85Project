package tw.RC.tutor;

public class RC67 {

	public static void main(String[] args) {
		//RC671.m2();//呼叫m2 static方法的時候,static類別會先載入((只會載入1次
		//RC671.m2();
		//RC671.m2();
//		RC671 obj1, obj2;
//		obj1 = new RC671();//呼叫建構式時,static類別也會先載入((只會載入一次
//		obj2 = new RC671();
		
		RC672 obj1 = new RC672();
	}

}
class RC671{
	
		static {System.out.println("RC671:static{}");}
		{System.out.println("RC671:{}");}
		
		RC671(){System.out.println("RC671()");}
		
		static void m2() {System.out.println("RC671:static:m2()");}
		void m1() {System.out.println("RC671:m1()");}
		
}
class RC672 extends RC671 {
	static {System.out.println("RC672:static{}");}

}