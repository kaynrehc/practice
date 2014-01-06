package com.enthusys.threadplay;

/**
 * User: mchernyak
 * Date: 1/6/14
 * Time: 12:07 PM
 */
public class ThreadUnsafe {

	private static int commonCounter = 0;
	private static Object x = new Object();

	public static void resetCount() {
		commonCounter = 0;
	}

	public static int getCount() {
		return commonCounter;
	}


	public void addCount(int n) {
		// operation on a static variable has to be synchronized on a static object monitor:
		// synchronized (this) - will not be thread safe

		synchronized (x) {
			commonCounter += n;
		}
	}
}
