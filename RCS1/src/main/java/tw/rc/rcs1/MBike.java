package tw.rc.rcs1;

public class MBike implements Bike{

	@Override
	public void upSpeed() {
		System.out.println("MBike:upSpeed()");
	}

	@Override
	public void downSpeed() {
		System.out.println("MBike:downSpeed()");		
	}

}
