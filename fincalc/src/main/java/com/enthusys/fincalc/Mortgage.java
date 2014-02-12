package com.enthusys.fincalc;

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

	public Mortgage(MortgageType mortgageType, double principalAmount, int termYears, double apr) {
		this.mortgageType = mortgageType;
		this.principalAmount = principalAmount;
		this.termYears = termYears;
		this.apr = apr;
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
		this.principalAmount = principalAmount;
	}

	public int getTermYears() {
		return termYears;
	}

	public void setTermYears(int termYears) {
		this.termYears = termYears;
	}

	public double getApr() {
		return apr;
	}

	public void setApr(double apr) {
		this.apr = apr;
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
