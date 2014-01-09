package com.enthusys.play.vehicle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehicleTest {
	private static final Logger logger = LoggerFactory.getLogger(VehicleTest.class);

	@Test
	public void testCar() {
		Car car = new Car("Maria");
		logger.info("*** car: {}", car);

		// ok to access public field
		logger.info("*** pubX: {}", car.pubX);

		// ok to access package-private field
		logger.info("*** pprX: {}", car.pprX);

		// ok to access protected field in the same package
		logger.info("*** proX: {}", car.proX);

		// not ok to access private field
		// logger.info("*** proX: {}", car.priX);

		logger.info("");
		Minicar minicar = new Minicar("Jib");
		logger.info("*** minicar: {}", minicar);

	}
}
