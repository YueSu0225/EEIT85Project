package tw.RC.tutor;

public class RC20 {
	public static void main(String[] args) {
		RC201 obj1 = new RC201();
		RC202 obj2 = new RC202();
		RC203 obj3 = new RC203();
		att(obj1);//呼叫怪物方法
		att(obj2);//呼叫怪物方法
		att(obj3);//呼叫怪物方法
	}
	
	static void att(RC201 XX) { //怪物攻擊的方法帶進去att()
		XX.m1();
	}
	
}
class RC201 {//假設為怪物第一代
	void m1() {System.out.println("RC201:m1()");}//假設為怪物攻擊方法
}
class RC202 extends RC201 {//怪物第2代
	void m1() {System.out.println("RC202:m1()");}
}
class RC203 extends RC201 {//怪物第2代不同種類
	void m1() {System.out.println("RC203:m1()");}
}

