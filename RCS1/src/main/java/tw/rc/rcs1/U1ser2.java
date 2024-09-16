package tw.rc.rcs1;

import org.springframework.beans.factory.annotation.Autowired;

public class U1ser2 {
	@Autowired
	private Bike bike;
	
	
	public void rideBike() {
		bike.upSpeed();

	}
	
}
