package tw.RC.tutor;

public class RC38 {
	public static void main(String[] args) {
		m1();
		System.out.println("Game Over");
	}
	
	static void m1() {
		System.out.println("1");
		int[] a = {1, 2, 3, 4};
		
		try {
			System.out.println(a[10]);
			System.out.println("RC");
//			return;
		} catch (Exception e) {
			System.out.println(e);
			return;//return後下面程式不跑,除了finally,它一樣會執行
		}finally {//整個try,catch 的結尾
			System.out.println("f");
		}
		
		System.out.println("OK");
		
	}
	
}
