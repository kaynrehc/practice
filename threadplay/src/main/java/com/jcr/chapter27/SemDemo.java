package com.jcr.chapter27;

import java.util.concurrent.Semaphore;

class SemDemo {

	public static void main(String args[]) {
		SemDemo sd = new SemDemo();
		Semaphore sem = new Semaphore(1);

		new XThread(sem, "A", 1, 5);
		new XThread(sem, "B", -1, 5);

	}
}
