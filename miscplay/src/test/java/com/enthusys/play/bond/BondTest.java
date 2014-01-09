package com.enthusys.play.bond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BondTest {
	private static final Logger logger = LoggerFactory.getLogger(BondTest.class);

	@Test
	public void testBond() {
		Bond b1 = new Bond();
		logger.info("**** {}", b1);

		Calendar cal30yrs = Calendar.getInstance();
		cal30yrs.set(2044, 5, 31, 0, 0);
		Calendar cal15yrs = Calendar.getInstance();
		cal30yrs.set(2029, 5, 31, 0, 0);


		Bond b2 = new Bond("US Treasury", 30, cal30yrs.getTime(), 10000.0F, 7.0F);
		logger.info("**** {}", b2);

		Bond b3 = new Bond();
		b3.setIssuer("US Treasury");
		b3.setTerm(30);
		b3.setMaturityDate(cal30yrs.getTime());
		b3.setNotionalAmount(10000.0F);
		b3.setCouponRate(7.0F);
		logger.info("**** {}", b3);

		Bond b4 = new Bond("US Treasury", 30, cal30yrs.getTime(), 10000.0F, 0.0F);
		b4.setCouponRate(7.0000F);
		logger.info("**** {}", b4);

		assertFalse(b1.equals(b2));

		// Reflexive: For any non-null reference value x, x.equals(x) must return true.
		assertTrue(b1.equals(b1));

		// Symmetric: For any non-null reference values x and y, x.equals(y) must return
		// true if and only if y.equals (x)
		assertTrue(b2.equals(b3));
		assertTrue(b3.equals(b2));

		// Transitive: For any non-null reference values x, y, z, if x.equals(y) returns true
		// and y.equals(z) returns true, then x.equals(z) must return true.
		//
		// Consistent: For any non-null reference values x and y, multiple invocations of x.equals(y)
		// consistently return true or consistently return false, provided no information used in equals
		// comparisons on the objects is modified.
		assertTrue(b3.equals(b4));
		assertTrue(b2.equals(b4));

		// For any non-null reference value x, x.equals(null) must return false.
		Bond b5 = null;
		assertFalse(b3.equals(b5));
		b5 = new Bond("UK Treasury", 15, cal15yrs.getTime(), 1000.0F, 10.1F);

		Bond b6 = b5;
		assertEquals(b5,b6);

		logger.info("***** b1 {}", b1.hashCode());
		logger.info("***** b2 {}", b2.hashCode());
		logger.info("***** b3 {}", b3.hashCode());
		logger.info("***** b4 {}", b4.hashCode());
		logger.info("***** b5 {}", b5.hashCode());
		logger.info("***** b6 {}", b6.hashCode());

	}
}
