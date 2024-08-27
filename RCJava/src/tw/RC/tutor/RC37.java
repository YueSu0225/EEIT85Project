package tw.RC.tutor;

public class RC37 {
	public static void main(String[] args) {
		RC371 obj = new RC371();
		try {
			obj.m1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class RC371 {
	void m1 () throws Exception{
		System.out.println("RC371:m1()");
		m2();
	}
	void m2 () throws Exception{
		System.out.println("RC371:m2()");
		m3();
	}
	void m3 () throws Exception{
		System.out.println("RC371:m3()");
		throw new Exception();
	}
}