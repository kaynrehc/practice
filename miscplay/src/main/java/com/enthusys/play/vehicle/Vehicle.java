package com.enthusys.play.vehicle;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 8:18 PM
 */
public interface Vehicle {

	public String getName();

	public void setName(String name);

	public void doOperate();

	public void specialOperate(String hint);

}
