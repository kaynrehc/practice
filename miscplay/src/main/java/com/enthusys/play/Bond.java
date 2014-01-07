package com.enthusys.play;

import java.util.Date;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 6:51 PM
 */
public class Bond {
	private String issuer;
	private int term;
	private Date maturityDate;
	private float notionalAmount;
	private float couponRate;

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof Bond))
			return false;

		Bond rhs = (Bond) obj;

		if (this.issuer == null ? rhs.issuer == null : issuer.equals(rhs.issuer) &&
				this.term == rhs.term &&
				this.issuer == null ? rhs.maturityDate == null : issuer.equals(rhs.issuer) &&
				Float.compare(this.notionalAmount, rhs.notionalAmount) == 0 &&
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

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
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

	public float getNotionalAmount() {
		return notionalAmount;
	}

	public void setNotionalAmount(float notionalAmount) {
		this.notionalAmount = notionalAmount;
	}

	public float getCouponRate() {
		return couponRate;
	}

	public void setCouponRate(float couponRate) {
		this.couponRate = couponRate;
	}
}
