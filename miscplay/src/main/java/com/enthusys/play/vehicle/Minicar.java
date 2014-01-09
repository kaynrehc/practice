package com.enthusys.play.vehicle;

/**
 * User: mchernyak
 * Date: 1/9/14
 * Time: 9:14 AM
 */
public class Minicar extends Car {

	public Minicar(String name) {
		super(name);
	}

	@Override
	public String toString() {
		String rv = getName();

		// okay to access public
		rv += ": " + pubX;
		// okay to access protected
		rv += "-" + proX;
		// okay to access package-private
		rv += "-" + pprX;

		// not okay to access private
		//rv += priX;

		return rv;
	}

	@Override
	public void doOperate() {
		super.doOperate();
	}
}
