package tw.RC.tutor;

public class RC19 {

	public static void main(String[] args) {
		RC191 obj1 = new RC191();
		obj1.m1(); obj1.m2();
		
		RC192 obj2 = new RC192();
		obj2.m1(); obj2.m2(); obj2.m3();
		
		RC191 obj3 = new RC192();//宣告obj3 = 實作RC192()
		obj3.m1(); obj3.m2();
		
		RC192 obj4 = (RC192)obj3;//將(RC192)obj3強制轉回RC192()
		obj4.m1(); obj4.m2(); obj4.m3();
		
//		RC192 obj5 = new RC193();//因為RC192與RC193是旁系關西,
//		RC193 obj6 = new RC192();//            所以沒辦法轉型
		
		RC191 obj7 = new RC192();
//		RC193 obj8 = (RC193)obj7;
		
		if (obj7 instanceof Object) {
			System.out.println("Yes1");	
		}else {
			System.out.println("NO1");	
		}
		
		if (obj7 instanceof RC191) {
			System.out.println("Yes2");	
		}else {
			System.out.println("NO2");	
		}
		
		if (obj7 instanceof RC192) {
			System.out.println("Yes3");	
		}else {
			System.out.println("NO3");	
		}
		
		if (obj7 instanceof RC193) {//因為obj7與被轉換為RC191,所以可以編譯
			System.out.println("Yes4");	
		}else {
			System.out.println("NO4");	
		}
		
//		if (obj7 instanceof String) {//因為obj7與String一點關西都沒有,所以沒辦法編譯
//			System.out.println("Yes5");	
//		}else {
//			System.out.println("NO5");	
//		} 
		
		
	}
}
class RC191 {
	void m1() {
		System.out.println("RC191:m1()");
	}
	void m2() {
		System.out.println("RC191:m2()");
	}
}
class RC192 extends RC191 {//RC192物件實體is a RC191
	void m2() {
		System.out.println("RC192:m2()");
	}
	void m3() {
		System.out.println("RC192:m3()");
	}
}
class RC193 extends RC191 {
	void m2() {
		System.out.println("RC193:m2()");
	}
	void m3() {
		System.out.println("RC193:m3()");
	}
}