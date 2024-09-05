package tw.RC.tutor;

public class RC35 {

	public static void main(String[] args) {
		int a = 10, b = 3;
		int [] d = {0, 1, 2, 3};
		
		try {
			int c = a / b;
			System.out.println(c);
			System.out.println(d[12]);
		}catch (ArithmeticException e) {//數學捕捉
			System.out.println("Err");//出例外時捕捉執行,然後會繼續執行外面的code
		}catch (ArrayIndexOutOfBoundsException e) {//陣列捕捉
			System.out.println("Err2");
		}catch (RuntimeException e) {//RuntimeException,例外捕捉最廣
			System.out.println("Err3");
		}
		System.out.println("OK");
	}

}
