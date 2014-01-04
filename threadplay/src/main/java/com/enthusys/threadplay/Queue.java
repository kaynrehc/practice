package com.enthusys.threadplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Queue {
	private static final Logger logger = LoggerFactory.getLogger(Queue.class);
	private int myValue;

    public Queue() {
        logger.debug("***** Queue()");
        myValue = -1;
    }

    public synchronized void put(int x) {

        while (myValue != -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myValue = x;
        notify();
    }

    public synchronized int get() {

        while (myValue == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int rv = myValue;
        myValue = -1;
        notify();
        return rv;
    }
}
