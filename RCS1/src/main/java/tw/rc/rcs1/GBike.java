package tw.rc.rcs1;

public class GBike implements Bike{
	private int gear;
	private double speed;
	
	public int getGear() {
		return gear;
	}

	public double getSpeed() {
		return speed;
	}

	public void setGear(int gear) {
		this.gear = gear;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public void upSpeed() {
		System.out.println("GBike:upSpeed()");
	}

	@Override
	public void downSpeed() {
		System.out.println("GBike:downSpeed()");
	}
	
}
