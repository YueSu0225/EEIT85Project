package tw.RC.tutor;

import java.util.Scanner;

public class RC02 {

	public static void main(String[] args) {
		// input 
		Scanner scanner = new Scanner(System.in); //Scanner 是物件
		
		System.out.print("a = ");
		int a = scanner.nextInt();
		
		System.out.print("b = ");
		int b = scanner.nextInt();
		
		// op..運算
		int r = a + b;
		int r1 = a - b;
		int r2 = a * b;
		int r3 = a / b;
		int r4 = a % b;
		
		// output
		System.out.printf("%d + %d = %d\n", a, b, r);
		System.out.printf("%d - %d = %d\n", a, b, r1);
		System.out.printf("%d * %d = %d\n", a, b, r2);
		System.out.printf("%d / %d = %d...... %d\n", a, b, r3, r4);
	}

}
