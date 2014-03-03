package com.enthusys.play.bond;

import com.enthusys.play.Visitable;
import com.enthusys.play.Visitor;

import java.util.Date;
import java.util.Random;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 6:51 PM
 */
public class Bond implements Visitable, Comparable<Bond> {
	private String issuer;
	private long term;
	private Date maturityDate;
	private double notionalAmount;
	private float couponRate;

	@Override
	public void accept(Visitor visitor) {
		 visitor.visit(this);
	}

	public double calculateValue(double inputX) {
		double rv = 0;

		rv = new Random().nextDouble();

		if (rv < 0) {
			rv *= -1.0;
		}
		return rv;
	}

	public Bond(String issuer, int term, Date maturityDate, float notionalAmount, float couponRate) {
		this.issuer = issuer;
		this.term = term;
		this.maturityDate = maturityDate;
		this.notionalAmount = notionalAmount;
		this.couponRate = couponRate;
	}

	public Bond() {
		this.issuer = null;
		this.term = 0;
		this.maturityDate = null;
		this.notionalAmount = 0.0F;
		this.couponRate = 0.0F;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = (issuer == null) ? 0 : issuer.hashCode();
		result = 31 * result + (int) (term ^ (term >>> 32));
		result = 31 * result + ((maturityDate == null) ? 0 : maturityDate.hashCode());
		temp = Double.doubleToLongBits(notionalAmount);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (couponRate != +0.0f ? Float.floatToIntBits(couponRate) : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Bond))
			return false;

		Bond rhs = (Bond) obj;

		if (this.issuer == null ? rhs.issuer == null : issuer.equals(rhs.issuer) &&
				this.term == rhs.term &&
				this.issuer == null ? rhs.maturityDate == null : issuer.equals(rhs.issuer) &&
				Double.compare(this.notionalAmount, rhs.notionalAmount) == 0 &&
				Float.compare(this.couponRate, rhs.couponRate) == 0) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return "Bond{" +
				"issuer='" + issuer + '\'' +
				", maturityDate=" + maturityDate +
				", notionalAmount=" + notionalAmount +
				", couponRate=" + couponRate +
				'}';
	}

	@Override
	public int compareTo(Bond o) {
		return 0;
	}

	public long getTerm() {
		return term;
	}

	public void setTerm(long term) {
		this.term = term;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public double getNotionalAmount() {
		return notionalAmount;
	}

	public void setNotionalAmount(double notionalAmount) {
		this.notionalAmount = notionalAmount;
	}

	public float getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(float couponRate) {
		this.couponRate = couponRate;
	}
}
