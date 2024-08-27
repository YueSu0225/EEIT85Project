package tw.RC.tutor;

public class RC04 {

	public static void main(String[] args) {
		double temp = Math.random();
//		System.out.println(temp);
		int score = (int)(temp * 101) + 0;    
		// 整數0 ~ 100 (int): 轉成整數
		// 101代表意義為 從0開始數 101 個整數 +0代表意義為 從0開始
		// 若是要 1~10 亂數,就是 (temp * 10 ) +1 
		System.out.println(score);
		
		if (score >= 90) {
			System.out.println("A");
		}else if (score >=80 ) {
			System.out.println("B");
		}else if (score >=70 ) {
			System.out.println("C");
		}else if (score >=60 ) {
			System.out.println("D");
		}else {
			System.out.println("E");
		}	
	}
}