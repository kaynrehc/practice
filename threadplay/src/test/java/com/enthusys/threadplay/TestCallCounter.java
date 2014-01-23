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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

//@Ignore
@RunWith(value = Parameterized.class)
public class TestCallCounter {
	private static final Logger logger = LoggerFactory.getLogger(TestCallCounter.class);

	private int nThreads;
	private int nIterations;
	private int nExpectedNumber;

	@Parameters
	public static Collection<Integer[]> getParameters() {
		return Arrays.asList(new Integer[][]{
				{3, 0},
				{3, 1},
				{15, 2},
				{4, 1000},
				{5, 2000},
				{6, 12345},
//				{5, 5133515}
		});
	}

	public TestCallCounter(int nThreads, int nIterations) {
		this.nThreads = nThreads;
		this.nIterations = nIterations;
		this.nExpectedNumber = nIterations * nThreads;
	}

	// @Test
	public void testCallCounter() {

		CallCounter callCounter = new CallCounter();

		Thread[] threads = new Thread[nThreads];

		for (int i = 0; i < nThreads; i++)
			threads[i] = new Thread(new SimpleCaller(callCounter, nIterations));

		for (int i = 0; i < nThreads; i++)
			threads[i].start();

		try {
			for (int i = 0; i < nThreads; i++)
				threads[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("call counter test", nExpectedNumber, callCounter.getCallCounter());

		logger.info("***** testCallCounter() stops threads:{}, iterations:{}, expected calls:{}", nThreads,
				nIterations, nExpectedNumber);
	}

	@Test
	public void testCallCounterUsingExecutor() {
		CallCounter callCounter = new CallCounter();
//		Executor exec = Executors.newFixedThreadPool(nThreads);
		Executor exec = Executors.newCachedThreadPool();
		CountDownLatch stopCountDownLatch = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++)
			exec.execute(new SimpleCaller(callCounter, nIterations, stopCountDownLatch));

		try {
			stopCountDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("***** testCallCounterUsingExecutor() stops threads:{}, iterations:{}, expected calls:{}",
				nThreads, nIterations, nExpectedNumber);

		assertEquals("call counter test", nExpectedNumber, callCounter.getCallCounter());

	}
}
