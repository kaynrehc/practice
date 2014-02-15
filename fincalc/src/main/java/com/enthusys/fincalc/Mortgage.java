package com.enthusys.fincalc;

import java.util.Calendar;
import java.util.Date;

/**
 * User: mchernyak
 * Date: 2/11/14
 * Time: 12:02 PM
 */
public class Mortgage {
	private MortgageType mortgageType;
	private double principalAmount;
	private int termYears;
	private double apr;
	private Date startDate;
	private Date maturityDate;
	private int numberOfMonthlyPayments;
	private double paymentAmount;

	public Mortgage(MortgageType mortgageType, double principalAmount, int termYears, double apr) {
		this.mortgageType = mortgageType;
		this.principalAmount = principalAmount;
		this.termYears = termYears;
		this.apr = apr;
		this.numberOfMonthlyPayments = termYears * 12;
		calculatePaymentAmount();
	}

	private void calculatePaymentAmount() {
		double pmt = ((apr / 100 / 12) * principalAmount) /
				(1 - Math.pow((1 + (apr / 100 / 12)), ((-1) * numberOfMonthlyPayments)));
		paymentAmount = Math.round(pmt * 100.0) / 100.0;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public int getNumberOfMonthlyPayments() {
		return numberOfMonthlyPayments;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.YEAR, termYears);
		maturityDate = calendar.getTime();
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public MortgageType getMortgageType() {
		return mortgageType;
	}

	public void setMortgageType(MortgageType mortgageType) {
		this.mortgageType = mortgageType;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		if (principalAmount < 1.0 || principalAmount > 1000000.0) {
			throw new IllegalArgumentException("principal amount must be between 1 and 1000000");
		}
		this.principalAmount = principalAmount;
		calculatePaymentAmount();
	}

	public int getTermYears() {
		return termYears;
	}

	public void setTermYears(int termYears) {
		this.termYears = termYears;
		calculatePaymentAmount();
	}

	public double getApr() {
		return apr;
	}

	public void setApr(double apr) {
		if (apr < 0 ||apr > 100) {
			throw new IllegalArgumentException("apr must be between 1 and 100");
		}
		this.apr = apr;
		calculatePaymentAmount();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Mortgage mortgage = (Mortgage) o;

		if (Double.compare(mortgage.apr, apr) != 0) return false;
		if (Double.compare(mortgage.principalAmount, principalAmount) != 0) return false;
		if (termYears != mortgage.termYears) return false;
		if (mortgageType != mortgage.mortgageType) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = mortgageType != null ? mortgageType.hashCode() : 0;
		temp = Double.doubleToLongBits(principalAmount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + termYears;
		temp = Double.doubleToLongBits(apr);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Mortgage{" +
				"mortgageType=" + mortgageType +
				", principalAmount=" + principalAmount +
				", termYears=" + termYears +
				", apr=" + apr +
				", hashCode=" + hashCode() +
				'}';
	}
}
