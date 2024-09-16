package tw.rc.rcs1;

import org.springframework.stereotype.Component;


public class Bike1 implements Bike{

	@Override
	public void upSpeed() {
		System.out.println("Bike1:upSpeed");
	}

	@Override
	public void downSpeed() {
		System.out.println("Bike1:downSpeed");
	}
	
}
