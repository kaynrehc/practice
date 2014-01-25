package com.enthusys.play;

import com.enthusys.play.bond.Bond;

/**
 * User: mchernyak
 * Date: 1/24/14
 * Time: 6:12 PM
 */
public interface Visitor {
	public void visit(Bond bond);
}
