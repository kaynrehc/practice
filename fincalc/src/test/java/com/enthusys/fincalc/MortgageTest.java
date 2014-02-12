package com.enthusys.fincalc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNotNull;

/**
 * User: mchernyak
 * Date: 2/11/14
 * Time: 12:08 PM
 */
public class MortgageTest {
	private static final Logger log = LoggerFactory.getLogger(MortgageTest.class);

	/* 	done: create a mortgage with attributes - make sure the base attributes are set correctly
		done: test mortgage equality
		done: update mortgage type
		todo: start date - make sure start date set correctly
		todo: calculate end date - make sure the end date is set correctly
		todo: calculate the number of monthly payments
		todo: calculate the monthly payment amount
		todo: calculate the amount of principal and interest of a payment
	 */

	@Test
	public void testMortgageType() {
		Mortgage mortgage1 = createTestMortgage();

		MortgageType mtOriginal = mortgage1.getMortgageType();
		mortgage1.setMortgageType(MortgageType.ADJUSTABLE);

		MortgageType mtNew = mortgage1.getMortgageType();
		assertNotSame("should not be te same", mtOriginal, mtNew);
		assertEquals("should equal", MortgageType.ADJUSTABLE, mtNew);
	}

	@Test
	public void testCreate() {
		Mortgage mortgage = createTestMortgage();
		log.info("***** {}", mortgage);
		assertNotNull(mortgage);
		assertEquals("type", MortgageType.FIXED, mortgage.getMortgageType());
		assertEquals(100000, mortgage.getPrincipalAmount(), 0.000001);
		assertEquals(5.1, mortgage.getApr(), 0.000001);
		assertEquals("years", 30, mortgage.getTermYears());
	}

	@Test
	public void testEquals() {
		Mortgage mortgage1 = createTestMortgage();
		Mortgage mortgage2 = createTestMortgage();
		log.info("***** {}", mortgage1);
		log.info("***** {}", mortgage2);
		assertEquals("mortgages should be equals", mortgage1, mortgage2);

		int hash1 = mortgage1.hashCode();
		int hash2 = mortgage2.hashCode();
		assertEquals("hash codes should be equals", hash1, hash2);
	}

	private Mortgage createTestMortgage() {
		return new Mortgage(MortgageType.FIXED, 100000, 30, 5.1);
	}


}
