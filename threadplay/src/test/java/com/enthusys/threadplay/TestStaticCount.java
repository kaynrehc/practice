package com.enthusys.threadplay;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

//@Ignore
@RunWith(value = Parameterized.class)
public class TestStaticCount {
	private static final Logger logger = LoggerFactory.getLogger(TestStaticCount.class);

	private int nThreads;
	private int nIterations;
	private int nExpectedNumber;

	@Parameters
	public static Collection<Integer[]> getParameters() {
		return Arrays.asList(new Integer[][]{
				{1, 1},
				{2, 2},
				{5, 100},
				{5, 1000},
				{6, 12345},
		});
	}

	public TestStaticCount(int nThreads, int nIterations) {
		this.nThreads = nThreads;
		this.nIterations = nIterations;
		this.nExpectedNumber = nIterations * nThreads;
	}

	private class PrivateCaller implements Runnable {
		private ThreadUnsafe threadUnsafe;
		private int nIterations;

		public PrivateCaller(ThreadUnsafe threadUnsafe, int nIterations) {
			this.threadUnsafe = threadUnsafe;
			this.nIterations = nIterations;
		}

		public void run() {
			for (int i = 0; i < nIterations; i++) {
				this.threadUnsafe.addCount(1);
			}
		}
	}

	@Test
	public void testCallCounter() {

		//ThreadUnsafe threadUnsafe = new ThreadUnsafe();
		ThreadUnsafe.resetCount();

		Thread[] threads = new Thread[nThreads];

		for (int i = 0; i < nThreads; i++)
			threads[i] = new Thread(new PrivateCaller(new ThreadUnsafe(), nIterations));

		for (int i = 0; i < nThreads; i++)
			threads[i].start();

		try {
			for (int i = 0; i < nThreads; i++)
				threads[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("call counter test", nExpectedNumber, ThreadUnsafe.getCount());

		logger.info("***** testCallCounter() stops threads:{}, iterations:{}, expected calls:{}", nThreads,
				nIterations, nExpectedNumber);
	}
}
