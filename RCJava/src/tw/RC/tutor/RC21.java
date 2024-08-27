package tw.RC.tutor;

public class RC21 {
	public static void main(String[] args) {
		RC211 obj1 = new RC211() {//因為RC212為抽象類別
			@Override
			void m2() {//當場實作
				System.out.println("RC211:m2()");				
			}
		};//3.RC211 obj1<=>(宣告) = new RC211()<=>定義建構式
		obj1.m1();//4.呼叫
		
		RC211 obj2 = new RC212();
		obj2.m1(); obj2.m2();
	}
}
//抽象類別:由父類別來創造多型
abstract class RC211 {//1.類別 6.具有抽象方法,要變成抽象類別abstract
	void m1() {System.out.println("RC211:m1()");}//2.方法[名稱.參數.傳回]
	abstract void m2();//5.抽象方法:有定義沒有實作的方法
}
class RC212 extends RC211 {
	void m2() {System.out.println("RC212:m2()");}	
} 
class RC213 extends RC211 {
	void m2() {System.out.println("RC213:m2()");}	
} 