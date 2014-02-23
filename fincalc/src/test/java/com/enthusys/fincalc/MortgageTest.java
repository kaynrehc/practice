package com.enthusys.fincalc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * User: mchernyak
 * Date: 2/11/14
 * Time: 12:08 PM
 */
public class MortgageTest {
	private static final Logger log = LoggerFactory.getLogger(MortgageTest.class);

	/*	done: create a mortgage with attributes - make sure the base attributes are set correctly
		done: test set term
		done: test set/get start date
		done: test mortgage equality
		done: update mortgage type
		done: calculate end date - make sure the end date is set correctly
		done: calculate the number of monthly payments
		done: calculate the monthly payment amount
		todo: throw an illegal arg exception upon invalid principal, apr
	 */

	@Test
	public void testSetStartDate() {
		Date today = new Date();
		Mortgage mortgage = createTestMortgage();
		mortgage.setStartDate(today);
		assertEquals("dates should be equal", today, mortgage.getStartDate());
	}

	@Test
	public void testSetApr() {
		Mortgage mortgage = createTestMortgage();
		mortgage.setApr(5.0);
		assertEquals(5.0, mortgage.getApr(), 0.0001);
	}


	@Test
	public void testSetTerm() {
		Mortgage mortgage = createTestMortgage();
		mortgage.setTermYears(100);
		assertEquals(100, mortgage.getTermYears());
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testRejectIllegalPrincipal() {
		Mortgage mortgage = createTestMortgage();
		mortgage.setPrincipalAmount(-1);
	}

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testRejectIllegalApr() {
			Mortgage mortgage = createTestMortgage();
			mortgage.setApr(-1.0);
	}

	@Test
	public void testCalculatesPaymentAmount() {
		Mortgage mortgage = createTestMortgage();
		assertEquals(1264.14, mortgage.getPaymentAmount(), 0.0001);

		mortgage.setPrincipalAmount(250000);
		assertEquals(1580.17, mortgage.getPaymentAmount(), 0.0001);
	}

	@Test
	public void testCalculatesNumberOfMonthlyPayments() {
		Mortgage mortgage = createTestMortgage();
		mortgage.setStartDate(new Date());

		int expectedMonthlyPayments = 30 * 12;
		assertEquals("should be the same", expectedMonthlyPayments, mortgage.getNumberOfMonthlyPayments());
	}

	@Test
	public void testCalculatesMaturityDate() {
		// test method code - prep for actual test
		Calendar calendar30YearsFromNow = Calendar.getInstance();
		Date startDate = new Date();
		calendar30YearsFromNow.setTime(startDate);
		calendar30YearsFromNow.add(Calendar.YEAR, +30);

		Mortgage mortgage = createTestMortgage();
		mortgage.setStartDate(startDate);
		Date mortgageMaturityDate = mortgage.getMaturityDate();

		Calendar calendarMaturity = Calendar.getInstance();
		calendarMaturity.setTime(mortgageMaturityDate);

		assertNotSame(calendar30YearsFromNow, calendarMaturity);
		assertEquals(calendar30YearsFromNow, calendarMaturity);
	}

	@Test
	public void testSetsMortgageType() {
		Mortgage mortgage1 = createTestMortgage();

		MortgageType mtOriginal = mortgage1.getMortgageType();
		mortgage1.setMortgageType(MortgageType.ADJUSTABLE);

		MortgageType mtNew = mortgage1.getMortgageType();
		assertNotSame("should not be te same", mtOriginal, mtNew);
		assertEquals("should equal", MortgageType.ADJUSTABLE, mtNew);
	}

	@Test
	public void testCreatesMortgageInstance() {
		Mortgage mortgage = createTestMortgage();
		log.info("***** {}", mortgage);
		assertNotNull(mortgage);
		assertEquals("type", MortgageType.FIXED, mortgage.getMortgageType());
		assertEquals(200000, mortgage.getPrincipalAmount(), 0.000001);
		assertEquals(6.5, mortgage.getApr(), 0.000001);
		assertEquals("years", 30, mortgage.getTermYears());
	}

	@Test
	public void testVerifiesEqualsAndHashCode() {
		Mortgage mortgage1 = createTestMortgage();
		Mortgage mortgage2 = createTestMortgage();
		assertEquals("mortgages should be equals", mortgage1, mortgage2);

		int hash1 = mortgage1.hashCode();
		int hash2 = mortgage2.hashCode();
		assertEquals("hash codes should be equals", hash1, hash2);
	}

	private Mortgage createTestMortgage() {
		return new Mortgage(MortgageType.FIXED, 200000, 30, 6.5);
	}
}
