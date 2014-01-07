package com.enthusys.play;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

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
		assertTrue(b2.equals(b3));
		assertTrue(b3.equals(b2));
		assertTrue(b2.equals(b4));
		assertTrue(b4.equals(b2));
	}
}
