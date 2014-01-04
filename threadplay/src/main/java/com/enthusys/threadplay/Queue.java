package com.enthusys.threadplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Queue {
	private static final Logger logger = LoggerFactory.getLogger(Queue.class);
	private int myValue;
	private boolean valueSet = false;

    public Queue() {
        logger.debug("***** Queue()");
        myValue = -1;
    }

    public synchronized void put(int x) {

        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myValue = x;
		valueSet = true;
        notify();
    }

    public synchronized int get() {

        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int rv = myValue;
        myValue = -1;
		valueSet = false;
		notify();
        return rv;
    }
}
