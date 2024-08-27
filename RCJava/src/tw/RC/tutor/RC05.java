package tw.RC.tutor;

public class RC05 {

	public static void main(String[] args) {
		int year = 2004;
		boolean isLeap;
		
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year %400 == 0) {
					isLeap = true; // 2月有29天
				}else {
					isLeap = false; // 2月有28天
				}
			}else {
				isLeap = true; // 2月有29天
			}
		}else {
			isLeap = false; // 2月有28天
		}
		
		System.out.printf("%d年為%s年", year, isLeap?"閏":"平");
		
//		//另一種寫法
//		if (year % 400 == 0 || (year % 4 == 0 && year % 100 == 0)) {
//			//29
//		}else {
//			//28
//		}
		
	}

}
