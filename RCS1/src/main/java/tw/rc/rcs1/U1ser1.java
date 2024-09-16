package tw.rc.rcs1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class U1ser1 {
	@Autowired
	private Bike1 bike1;

	public void rideBike() {
		bike1.upSpeed();
		
	}
	
}
