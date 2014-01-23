package com.enthusys.threadplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * Date: 1/2/14
 * Time: 6:43 PM
 */
public class SimpleCaller implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private CountDownLatch stopCountDownLatch = null;

	private CallCounter callCounter;
	private int nCalls;

	public SimpleCaller(CallCounter callCounter, int nCalls) {
		logger.debug("***** SimpleCaller(nCalls:{})", nCalls);
		this.callCounter = callCounter;
		this.nCalls = nCalls;
	}

	public SimpleCaller(CallCounter callCounter, int nCalls, CountDownLatch stopCountDownLatch) {
		logger.debug("***** SimpleCaller(nCalls:{})", nCalls);
		this.callCounter = callCounter;
		this.nCalls = nCalls;
		this.stopCountDownLatch = stopCountDownLatch;
	}


	public void run() {
		for (int i=0; i < nCalls; i++)
			callCounter.callMe();
		logger.debug("***** SimpleCaller(nCalls:{}) stops", nCalls);

		if (stopCountDownLatch != null)
			stopCountDownLatch.countDown();
	}
}
