package com.enthusys.threadplay;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Date: 1/2/14
 * Time: 6:37 PM
 */
public class CallCounter {
//	private volatile int callCounter;
	private AtomicInteger callCounter;

	public CallCounter() {
		callCounter = new AtomicInteger(0);
	}

	public void callMe() {
//		synchronized (this) {
			callCounter.getAndIncrement();
//		}
	}

	public int getCallCounter() {
		return callCounter.get();
	}
}
