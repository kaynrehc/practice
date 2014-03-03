package com.enthusys.threadplay;

/**
 * User: mchernyak
 * Date: 2/24/14
 * Time: 12:05 PM
 */
public class AlphaWorker {

	public synchronized void performAlphaService(BetaWorker betaWorker) {
		String name = Thread.currentThread().getName();
		System.out.println("***** " + name + " entered AlphaWorker.performAlphaService()");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.out.println("***** performAlphaService() interrupted");
		}

		System.out.println("***** " + name + " trying to call betaWorker.last()");
		betaWorker.last();
	}

	public synchronized void last() {
		System.out.println("***** AlphaWorker.last() called by " + Thread.currentThread().getName());
	}


}
