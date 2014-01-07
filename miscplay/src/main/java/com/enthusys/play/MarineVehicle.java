package com.enthusys.play;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:27 PM
 */
public abstract class MarineVehicle implements Vehicle {
	String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void doOperate() {
		System.out.println("***** MarineVehicle.doOperate()");
	}
}
