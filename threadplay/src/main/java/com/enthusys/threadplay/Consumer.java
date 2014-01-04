package com.enthusys.threadplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: mchernyak
 * Date: 12/15/13
 * Time: 7:48 PM
 */
public class Consumer implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	private Queue q;
	private int myValue;
	private final int nIterations;

	public int getMyValue() {
		return myValue;
	}

	public Consumer(Queue q, int nIterations) {
		logger.debug("***** Consumer()");
		this.q = q;
		this.nIterations = nIterations;
	}

	@Override
	public void run() {
		for (int i = 1; i <= nIterations; i++) {
			myValue = q.get();
			logger.debug("***** consumed {}", myValue);
		}
		logger.debug("***** Consumer stops.");
	}

}
