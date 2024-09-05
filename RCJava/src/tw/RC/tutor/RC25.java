package tw.RC.tutor;

public class RC25 {
	public static void main(String[] args) {

	}
}
class RC251 {//名稱一樣(方便呼叫),參數(方法不同)個數型別不一樣都可以形成overload(覆載.超載)
	void m1() {}
	void m1(int a) {}
	public int mi(double a) {return 1;}
	protected Object m2() {return new Object();}
}
class RC252 extends RC251 {//override(覆蓋.覆寫)發生在繼承關西上extends
	public String m2() {
		return "";
		}
}
//override:改寫方法 方法名稱一樣參數個數型別一樣,傳回基本型別(包含void)要跟父類別完全一樣
//字串是Object:Object m2() {return new Object();}
//public比父類別的protected大所以可以覆寫