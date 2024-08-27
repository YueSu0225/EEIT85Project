package tw.RC.tutor;

public class PokerV2 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();//執行開始時間
		//洗牌
		int[] poker = new int[52];
		boolean isRepeat;
		int temp;
		for (int i = 0; i < poker.length; i++) {//跑52次
			
			do {
				temp = (int)(Math.random()*52);
				
				// 檢查機制:有無重複
				isRepeat = false;
				for (int j = 0; j < i; j++) {
					if (temp == poker[j]) {
						// 重複了
						isRepeat = true; 
						break;
					}
				}
			}
			while (isRepeat);

			poker[i] = temp;
			System.out.println(poker[i]);
		}
		System.out.println("-----");
		//執行開始時間-結束時間=所花時間
		System.out.println(System.currentTimeMillis()-start);
		
		
		//發牌
		
		//攤牌 + 理牌
	}

}
