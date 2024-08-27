package tw.RC.tutor;

public class RC18 {

	public static void main(String[] args) {
		RC183 obj = new RC183();
	}

}

class RC181 {
	RC181() {//會先執行System.out.println("RC181()");
		System.out.println("RC181()");
	}
}
class RC182 extends RC181 {
	RC182() {//隱含super會叫到上面的RC181()
		System.out.println("RC182()");
	}
}
class RC183 extends RC182 {
	RC183() {//隱含super會叫到上面的RC182()
		this(2);//會先叫到下面的RC183(int a)
		System.out.println("RC183()");
	}
	RC183(int a) {
		this("");//會先叫到下面的RC183(String a)
		System.out.println("RC183(int)");
	}
	RC183(String a) {//會先叫到上面的RC183()
		System.out.println("RC183(String)");
	}
}

//輸出:	RC181()
//		RC182()
//		RC183(String)
//		RC183(int)
//		RC183()
