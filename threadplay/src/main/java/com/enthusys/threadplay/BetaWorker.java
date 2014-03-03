package com.enthusys.threadplay;

/**
 * User: mchernyak
 * Date: 2/24/14
 * Time: 12:05 PM
 */
public class BetaWorker {

	public synchronized void performBetaService(AlphaWorker alphaWorker) {
		String name = Thread.currentThread().getName();
		System.out.println("***** " + name + " entered BetaWorker.performBetaService()");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.out.println("***** performBetaService() interrupted");
		}

		System.out.println("***** " + name + " trying to call alphaWorker.last()");
		alphaWorker.last();
	}

	public synchronized void last() {
		System.out.println("***** BetaWorker.last() called by " + Thread.currentThread().getName());
	}
}
