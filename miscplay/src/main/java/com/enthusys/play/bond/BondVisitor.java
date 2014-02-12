package com.enthusys.play.bond;

import com.enthusys.play.Visitable;
import com.enthusys.play.Visitor;

/**
 * User: mchernyak
 * Date: 1/24/14
 * Time: 6:17 PM
 */
public class BondVisitor implements Visitor {
	double totalValue = 0.0;

	@Override
	public void visit(Visitable visitable) throws IllegalArgumentException {
		if (visitable instanceof Bond) {
			totalValue += ((Bond) visitable).calculateValue(1);
		} else {
			throw new IllegalArgumentException("Expecting an instance of Bond class");
		}
	}

	public double getTotalValue() {
		return totalValue;
	}
}
