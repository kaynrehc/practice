package com.jcr.chapter27;

import java.util.concurrent.Semaphore;

// A thread of execution that decrements or increments the count.
class XThread implements Runnable {
	String name;
	Semaphore sem;
	int x, y;

	XThread(Semaphore s, String n, int x, int y) {
		sem = s;
		name = n;
		this.x = x;
		this.y= y;
		new Thread(this).start();
	}

	public void run() {

		System.out.println("Starting " + name);

		try {
			// First, get a permit.
			System.out.println(name + " is waiting for a permit.");
			sem.acquire();
			System.out.println(name + " gets a permit.");

			// Now, access shared resource.
			for (int i = 0; i < y; i++) {
				Shared.count += x;
				System.out.println(name + ": " + Shared.count);

				// Now, allow a context switch -- if possible.
				Thread.sleep(10);
			}
		} catch (InterruptedException exc) {
			System.out.println(exc);
		}

		// Release the permit.
		System.out.println(name + " releases the permit.");
		sem.release();
	}
}
