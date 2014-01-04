package com.enthusys.threadplay;

/**
 * Created with IntelliJ IDEA.
 * Date: 1/2/14
 * Time: 6:37 PM
 */
public class CallCounter {
	private int callCounter;

	public CallCounter() {
		callCounter = 0;
	}

	public void callMe() {
		synchronized (this) {
			callCounter++;
		}
	}

	public int getCallCounter() {
		return callCounter;
	}
}
