package com.enthusys.threadplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: mchernyak
 * Date: 12/15/13
 * Time: 7:40 PM
 */
public class Producer implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private Queue q;
	private final int nIterations;

    public Producer(Queue q, int nIterations) {
        logger.debug("***** Producer()");
        this.q = q;
		this.nIterations = nIterations;
	}

    @Override
    public void run() {
        for (int i=1; i<=nIterations; i++) {
            q.put(i);
			logger.debug("***** produced {}", i);
        }
        logger.debug("***** Producer stops.");
    }
}
