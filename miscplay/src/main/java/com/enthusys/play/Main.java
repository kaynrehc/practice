package com.enthusys.play;

import com.enthusys.play.vehicle.Car;
import com.enthusys.play.vehicle.LandVehicle;
import com.enthusys.play.vehicle.Vehicle;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:32 PM
 */
public class Main {
	public static void main(String[] args) {

		System.out.println();

		LandVehicle landVehicle = new Car();

		landVehicle.doOperate();
		landVehicle.specialOperate("x");
		landVehicle.specialOperate("y");

		System.out.println();

		Vehicle vehicle = new Car();

		vehicle.doOperate();
		vehicle.specialOperate("x");
		vehicle.specialOperate("y");
	}
}
