/**
 * User: mchernyak
 * Date: 3/2/14
 * Time: 1:05 PM
 */
public class HistoricEventCounterTest implements Runnable {
	private HistoricEventCounter historicEventCounter;
	private int nThreads = 2;

	public static void main(String[] args) {
		HistoricEventCounterTest historicEventCounterTest = new HistoricEventCounterTest();

	}

	public void test() {
		for (int i=0; i<nThreads; i++) {
			Thread thread = new Thread(this);
		}
	}

	@Override
	public void run() {
		long currentTime = System.currentTimeMillis();
		long stopTime = currentTime + 1000 * 60 * 3;



	}


}
