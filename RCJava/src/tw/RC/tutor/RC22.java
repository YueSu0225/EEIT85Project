package tw.RC.tutor;

public class RC22 {
	public static void main(String[] args) {
		RC221 obj1 = new RC222();//我obj1需要有RC221實作的介面=RC222()
		RC223 obj2 = new RC222();
		RC222 obj3 = new RC222();
		obj1.m1();obj1.m1(); //obj1只能有這兩方法
		obj2.m3();obj2.m4();//obj2只能有這兩方法
		obj3.m1(); obj3.m2(); obj3.m3(); obj3.m4();//obj3能有這4兩方法
	}
}
//定義介面,介面=規格
interface RC221 {
	void m1();//定義 輸入參數傳回什麼
	void m2();
}
interface RC223 {
	void m3();
	void m4();
}
class RC222 implements RC221, RC223 {//我是RC222我的父類別為object我會實作RC221
	public void m1() {}//介面本身就是公然,包含的方法就是public
	public void m2() {}//介面本身就是公然,包含的方法就是public
	public void m3() {}
	public void m4() {}
}