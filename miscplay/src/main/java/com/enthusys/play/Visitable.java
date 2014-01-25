package com.enthusys.play;

/**
 * User: mchernyak
 * Date: 1/24/14
 * Time: 6:12 PM
 */
public interface Visitable {
	public void accept(Visitor v);
}
