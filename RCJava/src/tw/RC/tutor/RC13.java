package tw.RC.tutor;

import tw.RC.apis.Scooter;

public class RC13 {

	public static void main(String[] args) {
		Scooter s1 = new Scooter();//在呼叫該類別的建構式
		s1.changeGear(1);
		s1.upSpeed(); s1.upSpeed();
		s1.downSpeed();
		System.out.println(s1.getSpeed());
		s1.changeGear(4);
		s1.upSpeed(); s1.upSpeed();
		System.out.println(s1.getSpeed());
		System.out.println(s1.getColor());
	}

}
