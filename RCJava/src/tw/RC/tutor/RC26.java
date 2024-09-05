package tw.RC.tutor;

public class RC26 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
interface RC261{
	void m1();
}
interface RC262{
	void m2();
}
interface RC263 extends RC261, RC262 {//RC263方法包含RC261,RC262的方法
	void m3();
}
class RC264 implements RC263 {
//類別使用RC263介面,所以要把RC261.RC262.RC263的方法都要實作
	@Override
	public void m1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void m2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void m3() {
		// TODO Auto-generated method stub
		
	}
	
}