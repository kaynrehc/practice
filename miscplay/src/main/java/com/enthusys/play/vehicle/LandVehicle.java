package com.enthusys.play.vehicle;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:23 PM
 */
public class LandVehicle implements Vehicle {
	private String name;

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
		System.out.println("***** LandVehicle.doOperate()");
	}

	@Override
	public void specialOperate(String hint) {
		if (hint.equals("x"))
			System.out.println("***** LandVehicle.specialOperate()");
		else
			doOperate();
	}

}
