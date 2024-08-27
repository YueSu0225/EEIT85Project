package tw.RC.tutor;

public class PokerV1 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();//執行開始時間
		//洗牌
		int[] poker = new int[52];
		for (int i = 0; i < poker.length; i++) {//跑52次
			int temp = (int)(Math.random()*52);//會出現重複
			
			// 檢查機制:有無重複
			boolean isRepeat = false;//第二步驟:那就是if外的為false
			for (int j = 0; j < i; j++) {
				if (temp == poker[j]) {
					// 重複了
					isRepeat = true; 
					//布林值第一步驟:檢查重複了使用true
					break;
				}
			}
			if (!isRepeat) {
			poker[i] = temp;
			System.out.println(poker[i]);
			}else {
				i--; //因為重複的關西,所以這段不算,所以for跑不只52次
			}
		}
		System.out.println("-----");
		//執行開始時間-結束時間=所花時間
		System.out.println(System.currentTimeMillis()-start);
		
		
		//發牌
		
		//攤牌 + 理牌
	}

}
