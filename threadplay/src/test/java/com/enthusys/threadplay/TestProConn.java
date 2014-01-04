package com.enthusys.threadplay;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value= Parameterized.class)
public class TestProConn {
	private static final Logger logger = LoggerFactory.getLogger(TestProConn.class);

	private int nIterations;

	@Parameters
	public static Collection<Integer[]> getParameters() {
		return Arrays.asList(new Integer[][] {
			{0},
			{1},
			{2},
			{1000},
			{2000},
			{5000},
		});
	}

	public TestProConn(int nIterations) {
		this.nIterations = nIterations;
	}

	@Test
	public void testProCon() {
		Queue q = new Queue();

		Consumer consumer = new Consumer(q,nIterations);
		Producer producer = new Producer(q,nIterations);

		Thread tConsumer = new Thread(consumer);
		tConsumer.start();

		Thread tProducer = new Thread(producer);
		tProducer.start();

		try {
			tProducer.join();
			tConsumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("***** testProCon() stops [{}]", nIterations);

		assertEquals("expected exact value", nIterations, consumer.getMyValue());
	}

}
