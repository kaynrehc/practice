package com.jcr.chapter27;

import java.util.concurrent.*;

class SemDemo {

	public static void main(String args[]) {
		Semaphore sem = new Semaphore(1);

		new XThread(sem, "A", 1, 10);
		new XThread(sem, "B", -1, 10);

	}
}
