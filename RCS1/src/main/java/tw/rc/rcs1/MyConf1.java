package tw.rc.rcs1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConf1 {
	@Bean("gBike")
	public Bike getGBike() {
		GBike gBike = new GBike();
		gBike.setGear(4);
		gBike.setSpeed(12.0);
		System.out.println("getGBike():" + gBike.getGear() + ":" + gBike.getSpeed());		
		
		return gBike;
	}
	@Bean("mBike")
	public Bike getMBike() {
		System.out.println("getMBike()");
		return new MBike();
	}

}
