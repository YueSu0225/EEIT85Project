package tw.RC.apis;

public class Scooter extends Bike {
	private int gear;
	private String color;
	
	public Scooter() {
		System.out.println("Scooter()");
		color = "Yellow"; //產生建構式初始值
	}
	public Scooter(String color) {
		// super();
		System.out.println("Scooter(String)");
		this.color = color; //讓使用者自訂顏色的建構式
	}
	
	public void changeGear(int gear) {//換檔概念
		if (gear >= 0 && gear <= 4) { //這裡的gear為參數的gear,以離自己最近為原則
			this.gear = gear;
		}
	}
	
	public Bike upSpeed() {
		if (gear > 0) {
			speed = speed < 1? 1 :speed * 1.8 * gear;
		
		}
		return this;
	}
	
	public String getColor() {
		return color;
	}

}
