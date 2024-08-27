package tw.RC.tutor;

import tw.RC.apis.Bike;

//import tw.RC.apis.Bike;


public class RC12 {

	public static void main(String[] args) {
		Bike myBike = new Bike(); Bike urBike = new Bike();
		System.out.println(myBike.getSpeed());
		myBike.upSpeed();myBike.upSpeed();myBike.upSpeed();myBike.upSpeed();
		myBike.downSpeed();
		System.out.println(myBike.getSpeed());
		System.out.println("-------");
		System.out.println(urBike.getSpeed());
		urBike.upSpeed();urBike.upSpeed();
		System.out.println(urBike.getSpeed());
		System.out.println("-------");
		
//		myBike.speed = 10.1;
//		System.out.println(myBike.speed);

		}
		
	}


