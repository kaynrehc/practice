package com.enthusys.threadplay;

import net.jcip.examples.MonitorVehicleTracker;
import net.jcip.examples.MutablePoint;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class TestVehicleTracker {
    private static final Logger logger = LoggerFactory.getLogger(TestVehicleTracker.class);
    private String[] vehicles = {"Taxi1", "Taxi2", "Taxi3", "Taxi4", "Taxi5", "Taxi6", "Bus1", "Bus2", "Bus3", "Bus4",
            "Bus5", "Bus6", "Truck1", "Truck2", "Truck3", "Truck4", "Truck5", "Truck6"};

    @Test
    public void testVehTracker() {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(2);

        logger.info("*** testVehTracker() start");


        Map vehicleLocations = new HashMap<String, MutablePoint>();

        for (int i = 0; i < vehicles.length; i++) {
            vehicleLocations.put(vehicles[i], new MutablePoint());
        }

        MonitorVehicleTracker vt = new MonitorVehicleTracker(vehicleLocations);
        PutVehicles pv = new PutVehicles(vt, startGate, endGate, 20);
        RenderVehicles rv = new RenderVehicles(vt, startGate, endGate, 20);
        Thread t1 = new Thread(pv);
        Thread t2 = new Thread(rv);

        t1.start();
        t2.start();

        startGate.countDown();

//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("*** testVehTracker() finish");

    }

    private class PutVehicles implements Runnable {
        private MonitorVehicleTracker vt;
        private int nIterations;
        private CountDownLatch startGate;
        private CountDownLatch endGate;

        private PutVehicles(MonitorVehicleTracker vt, CountDownLatch startGate, CountDownLatch endGate, int n) {
            this.startGate = startGate;
            this.endGate = endGate;
            this.vt = vt;
            this.nIterations = n;
        }

        public void run() {
            logger.info("*** {}", nIterations);

            Random randomVehicleIndex = new Random();
            Random randomX = new Random();
            Random randomY = new Random();
            try {
                startGate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < nIterations; i++) {
                int x = randomX.nextInt(10);
                int y = randomY.nextInt(10);
                String s = vehicles[randomVehicleIndex.nextInt(vehicles.length)];
                logger.info("+++ {} {} {}", s, x, y);
                vt.setLocation(s, x, y);
            }
            endGate.countDown();
        }
    }

    private class RenderVehicles implements Runnable {
        private MonitorVehicleTracker vt;
        private int nIterations;
        private CountDownLatch startGate;
        private CountDownLatch endGate;

        private RenderVehicles(MonitorVehicleTracker vt, CountDownLatch startGate, CountDownLatch endGate, int n) {
            this.startGate = startGate;
            this.endGate = endGate;
            this.vt = vt;
            this.nIterations = n;
        }

        public void run() {
            logger.info("*** {}", nIterations);

            Random randomVehicleIndex = new Random();
            try {
                startGate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < nIterations; i++) {
                String s = vehicles[randomVehicleIndex.nextInt(vehicles.length)];
                MutablePoint mp = vt.getLocation(s);
                logger.info("--- {} {} {}", s, mp.getX(), mp.getY());
            }
            endGate.countDown();
        }
    }


}
