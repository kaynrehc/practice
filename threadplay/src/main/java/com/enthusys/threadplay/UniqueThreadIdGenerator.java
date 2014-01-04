package com.enthusys.threadplay;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueThreadIdGenerator {

	private static final AtomicInteger atomicInteger = new AtomicInteger(100);

	private static final ThreadLocal<Integer> threadLocalInteger = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return atomicInteger.getAndIncrement();
		}
	};

	public static int getAtomicIntegerValue() {
		return atomicInteger.get();
	}

	public static int getThreadLocalIntegerValue() {
		return threadLocalInteger.get();
	}
}
