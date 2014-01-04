package com.enthusys.threadplay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class TestCallCounter {
	private static final Logger logger = LoggerFactory.getLogger(TestCallCounter.class);

	private int nIterations;
	private int nExpectedNumber;

	@Parameters
	public static Collection<Integer[]> getParameters() {
		return Arrays.asList(new Integer[][]{
				{0},
				{1},
				{2},
				{1000},
				{2000},
				{12345},
				{5133515}
		});
	}

	public TestCallCounter(int nIterations) {
		this.nIterations = nIterations;
		this.nExpectedNumber = nIterations * 3;
	}

	@Test
	public void testCallCounter() {
		CallCounter callCounter = new CallCounter();
		Thread t1 = new Thread(new SimpleCaller(callCounter, nIterations));
		Thread t2 = new Thread(new SimpleCaller(callCounter, nIterations));
		Thread t3 = new Thread(new SimpleCaller(callCounter, nIterations));

		t1.start();
		t2.start();
		t3.start();

		try {
			t3.join();
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("call counter test", nExpectedNumber, callCounter.getCallCounter());

		logger.info("***** testCallCounter() stops [{}][{}]", nIterations, nExpectedNumber);
	}
}
