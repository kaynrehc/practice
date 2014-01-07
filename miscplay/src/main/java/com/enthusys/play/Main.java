package com.enthusys.play;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:32 PM
 */
public class Main {
	public static void main(String[] args) {

		System.out.println();

		LandVehicle landVehicle1 = new LandVehicle();
		LandVehicle landVehicle2 = new Car();

		landVehicle1.doOperate();
		landVehicle2.doOperate();
		landVehicle2.specialOperate("x");
		landVehicle2.specialOperate("y");

		System.out.println();

		Vehicle vehicle1 = new LandVehicle();
		Vehicle vehicle2 = new Car();

		vehicle1.doOperate();
		vehicle2.doOperate();
		vehicle2.specialOperate("x");
		vehicle2.specialOperate("y");

		System.out.println();
//		Vehicle vehicle3 = new MarineVehicle();
//		vehicle3.specialOperate("x");
//		vehicle3.specialOperate("y");

	}
}
