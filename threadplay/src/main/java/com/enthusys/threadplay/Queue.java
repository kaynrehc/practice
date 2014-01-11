package com.enthusys.threadplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Queue {
	private static final Logger logger = LoggerFactory.getLogger(Queue.class);
	private int myValue;
	private boolean valueSet = false;
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public Queue() {
		logger.debug("***** Queue()");
		myValue = -1;
	}

	public void put(int x) {

		synchronized (lock1) {
			while (valueSet) {
				try {
					lock1.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			myValue = x;
			valueSet = true;
			lock1.notify();
		}
	}

	public int get() {
		synchronized (lock1) {
			while (!valueSet) {
				try {
					lock1.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int rv = myValue;
			myValue = -1;
			valueSet = false;
			lock1.notify();
			return rv;
		}
	}
}
