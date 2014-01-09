package com.enthusys.play.vehicle;

import com.enthusys.play.vehicle.LandVehicle;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:29 PM
 */
public class Car extends LandVehicle {

	public String pubX;
	private String priX;
	protected String proX;
	String pprX;

	public Car(String name) {
		setName(name);
		pubX = name + "-pubX";
		priX = name + "-priX";
		proX = name + "-proX";
		pprX = name + "-pprX";

	}

	@Override
	public String toString() {
		return "Car{" +
				"name='" + getName() + '\'' +
				", pubX='" + pubX + '\'' +
				", priX='" + priX + '\'' +
				", proX='" + proX + '\'' +
				", pprX='" + pprX + '\'' +
				'}';
	}

	public Car() {
		setName("unnamed");
	}

	@Override
	public void doOperate() {
		System.out.println("***** Car.doOperate");
	}
}
