package com.enthusys.threadplay;

/**
 * User: mchernyak
 * Date: 2/24/14
 * Time: 12:18 PM
 */
public class Deadlock implements Runnable {
	AlphaWorker alphaWorker = new AlphaWorker();
	BetaWorker betaWorker = new BetaWorker();

	Deadlock() {
		Thread.currentThread().setName("MainThread");
		Thread t = new Thread(this, "RacingThread");
		t.start();
		alphaWorker.performAlphaService(betaWorker);
		System.out.println("***** back in main thread");

	}

	@Override
	public void run() {
		betaWorker.performBetaService(alphaWorker);
		System.out.println("***** back in the other thread");
	}

	public static void main(String[] args) {
		new Deadlock();
	}

}
