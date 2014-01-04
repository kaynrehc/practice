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

import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(value = Parameterized.class)
public class TestThreadLocal extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(TestThreadLocal.class);

	private int nThreads;

	@Parameters
	public static Collection<Integer[]> getParameters() {
		return Arrays.asList(new Integer[][]{
				{5}
		});
	}

	public TestThreadLocal(int nThreads) {
		this.nThreads = nThreads;
	}

	private class InnerThreadRunner implements Runnable {
		private final String threadNum;

		private InnerThreadRunner(String threadNum) {
			this.threadNum = threadNum;
		}

		public void run() {
			for (int i = 0; i < 3; i++) {
				logger.info("***** {}, atomic Integer: {}, threadLocal Integer: {}",
						threadNum,
						UniqueThreadIdGenerator.getAtomicIntegerValue(),
						UniqueThreadIdGenerator.getThreadLocalIntegerValue());
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testThreadLocal() {

		Thread[] threads = new Thread[nThreads];

		for (int i = 0; i < nThreads; i++)
			threads[i] = new Thread(new InnerThreadRunner("" + i));

		for (int i = 0; i < nThreads; i++) {
			threads[i].start();
		}

		try {
			for (int i = 0; i < nThreads; i++)
				threads[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		assertTrue(1 == 1);

		logger.info("***** testThreadLocal() stops threads:{}", nThreads);
		logger.info("***** atomic Integer: {}, threadLocal Integer: {}",
				UniqueThreadIdGenerator.getAtomicIntegerValue(),
				UniqueThreadIdGenerator.getThreadLocalIntegerValue());
	}
}
