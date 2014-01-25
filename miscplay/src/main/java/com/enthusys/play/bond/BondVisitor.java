package com.enthusys.play.bond;

import com.enthusys.play.Visitor;

/**
 * User: mchernyak
 * Date: 1/24/14
 * Time: 6:17 PM
 */
public class BondVisitor implements Visitor {
	double totalValue = 0.0;

	@Override
	public void visit(Bond bond) {
		totalValue += bond.calculateValue(1);
	}

	public double getTotalValue() {
		return totalValue;
	}
}
