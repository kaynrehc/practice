package com.enthusys.threadplay;

/**
 * Created with IntelliJ IDEA.
 * User: mchernyak
 * Date: 12/15/13
 * Time: 7:48 PM
 */
public class Consumer implements Runnable {

	private Queue q;
	private int myValue;

	public int getMyValue() {
		return myValue;
	}

	public Consumer(Queue q) {
		System.out.println("***** Consumer()");
		this.q = q;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 100; i++) {
			myValue = q.get();
			System.out.println("***** got:" + myValue);
		}
		System.out.println("***** Consumer stops.");
	}

}
