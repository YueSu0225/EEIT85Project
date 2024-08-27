package tw.RC.apis;

import java.io.Serializable;

public class Bike extends Object implements Serializable{ //extends Object可省略
	protected double speed;
	
	public Bike() {
		System.out.println("Bike()");
	}
	
	public Bike upSpeed() {
		speed = speed < 1? 1 :speed * 1.4;
		return this;
	}
	
	public void downSpeed() {
		speed = speed < 1? 0 :speed * 0.7;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	@Override
	public String toString() {
		return "I am a Bike:"+ speed;
	}
}
