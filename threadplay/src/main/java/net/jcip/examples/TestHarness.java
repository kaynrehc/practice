package net.jcip.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * TestHarness
 * <p/>
 * Using CountDownLatch for starting and stopping threads in timing tests
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TestHarness {
	private static final Logger logger = LoggerFactory.getLogger(Preloader.class);

	public long timeTasks(int nThreads, final Runnable task)
			throws InterruptedException {

		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new Thread() {
				public void run() {
					try {
						startGate.await();
						try {
							task.run();
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException ignored) {
					}
				}
			};
			t.start();
		}

		long start = System.currentTimeMillis();
		startGate.countDown();
		endGate.await();
		return System.currentTimeMillis() - start;
	}


	public static void main(String[] args) {
		logger.info("***** main() start");

		TestHarness testHarness = new TestHarness();

		Runnable r = new Runnable() {
			@Override
			public void run() {
				logger.info("***** starting task");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				logger.info("***** ending task");
			}
		};


		try {
			long milliseconds = testHarness.timeTasks(10, r);
			logger.info("***** milliseconds: {}", milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("***** main() finish");
	}
}
