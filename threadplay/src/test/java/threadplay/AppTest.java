package threadplay;

import com.enthusys.threadplay.*;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest
		extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	@Test
	public void testProCon() {
		Queue q = new Queue();

		Consumer consumer = new Consumer(q);
		Producer producer = new Producer(q);

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

		System.out.println("***** testApp() stops");

		assertEquals("expected exact value", 100, consumer.getMyValue());
	}

	@Test
	public void testCallCounter() {
		CallCounter callCounter = new CallCounter();
		Thread t1 = new Thread(new SimpleCaller(callCounter,1000));
		Thread t2 = new Thread(new SimpleCaller(callCounter,1000));

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals("call counter test", 2000, callCounter.getCallCounter());

		System.out.println("***** testCallCounter() stops");
	}


}
