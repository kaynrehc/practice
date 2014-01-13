package com.enthusys.threadplay;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.jcip.examples.MonitorVehicleTracker;
import net.jcip.examples.MutablePoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestVehicleTracker {
	private static final Logger logger = LoggerFactory.getLogger(TestVehicleTracker.class);
	private String[] vehicles = {"Taxi1", "Taxi2", "Taxi3", "Taxi4", "Taxi5", "Taxi6", "Bus1", "Bus2", "Bus3", "Bus4",
			"Bus5", "Bus6", "Truck1", "Truck2", "Truck3", "Truck4", "Truck5", "Truck6"};

	@Test
	public void testVehTracker() {

		logger.info("*** testVehTracker() start");

		Map vehicleLocations = new HashMap<String, MutablePoint>();

		for (int i = 0; i < vehicles.length; i++) {
			vehicleLocations.put(vehicles[i], new MutablePoint());
		}

		MonitorVehicleTracker vt = new MonitorVehicleTracker(vehicleLocations);
		PutVehicles pv = new PutVehicles(vt, 100);
		RenderVehicles rv = new RenderVehicles(vt, 150);
		Thread t1 = new Thread(pv);
		Thread t2 = new Thread(rv);

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("*** testVehTracker() finish");

	}

	private class PutVehicles implements Runnable {
		private MonitorVehicleTracker vt;
		private int nIterations;

		private PutVehicles(MonitorVehicleTracker vt, int n) {
			this.vt = vt;
			this.nIterations = n;
		}

		public void run() {
			logger.info("*** {}", nIterations);

			Random randomVehicleIndex = new Random();
			Random randomX = new Random();
			Random randomY = new Random();

			for (int i = 0; i < nIterations; i++) {
				int x = randomX.nextInt(10);
				int y = randomY.nextInt(10);
				String s = vehicles[randomVehicleIndex.nextInt(vehicles.length)];
				logger.info("+++ {} {} {}", s, x, y);
				vt.setLocation(s, x, y);
			}
		}
	}

	private class RenderVehicles implements Runnable {
		private MonitorVehicleTracker vt;
		private int nIterations;

		private RenderVehicles(MonitorVehicleTracker vt, int n) {
			this.vt = vt;
			this.nIterations = n;
		}

		public void run() {
			logger.info("*** {}", nIterations);

			Random randomVehicleIndex = new Random();

			for (int i = 0; i < nIterations; i++) {
				String s = vehicles[randomVehicleIndex.nextInt(vehicles.length)];
				MutablePoint mp = vt.getLocation(s);
				logger.info("--- {} {} {}", s, mp.getX(), mp.getY());
			}
		}
	}


}
