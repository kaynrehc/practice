package threadplay;

import com.enthusys.threadplay.Consumer;
import com.enthusys.threadplay.Producer;
import com.enthusys.threadplay.Queue;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
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
}
