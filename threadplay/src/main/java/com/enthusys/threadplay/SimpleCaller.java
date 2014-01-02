package com.enthusys.threadplay;

/**
 * Created with IntelliJ IDEA.
 * Date: 1/2/14
 * Time: 6:43 PM
 */
public class SimpleCaller implements Runnable {

	private CallCounter callCounter;
	private int nCalls;

	public SimpleCaller(CallCounter callCounter, int nCalls) {
		this.callCounter = callCounter;
		this.nCalls = nCalls;
	}

	public void run() {
		for (int i=0; i < nCalls; i++)
			callCounter.callMe();
	}
}
