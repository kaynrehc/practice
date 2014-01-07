package com.enthusys.play;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:29 PM
 */
public class Car extends LandVehicle {

	public Car(String name) {
		setName(name);
	}

	public Car() {
		setName("unnamed");
	}

	@Override
	public void doOperate() {
		System.out.println("***** Car.doOperate");
	}
}
