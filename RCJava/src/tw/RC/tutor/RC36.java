package tw.RC.tutor;

public class RC36 {
	public static void main(String[] args) {
		Bird b1 = new Bird();
		try {//呼叫者,面對出狀況寫法
			b1.setLeg(7);//超出範圍
		}catch (Exception e) {//拋出例外
			System.out.println("Oooop!");
		}
	}
}

class Bird { //小鳥類別
	private int leg;//小鳥有腳  
	
	void setLeg(int leg) throws Exception {//讓人可以設定小鳥有幾隻腳,throws Exception我可能會拋出例外
		if (leg >= 0 && leg <=2) {//設定小鳥的腳0,1,2隻
			this.leg = leg;
		}else {
			throw new Exception();//拋出例外,出錯拋給呼叫的人
		}
	}
}
