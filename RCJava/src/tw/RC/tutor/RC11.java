package tw.RC.tutor;

public class RC11 {

	public static void main(String[] args) {
		//整數才能使用的值交換
		int a = 10, b = 3;
		a = a + b; // a = 13
		b = a - b; // b = 10
		a = a - b; // a = 3
		System.out.printf("a = %d, b = %d", a, b);
		//a = 3, b = 10;
		System.out.println("");
		int x = 10, y = 3;
		x = x ^ y; // 定義
		y = x ^ y; // (x^y)^y = x^y,y^y = x^0  所以y = x 
		x = x ^ y; // x^(x^y) = x^x,x^y = 0^y  所以x = y
		System.out.printf("x = %d, y = %d\n", x, y);
		//x = 3, y = 10;
}

}
