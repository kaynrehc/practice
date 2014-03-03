/**
 * User: mchernyak
 * Date: 3/2/14
 * Time: 12:34 PM
 */
public class HistoricEventCounter {
	private static final int NUMBER_OF_MINUTE_COUNTS = 5;
	private static final long PERIOD_MILLISECONDS = 1000 * 60 * NUMBER_OF_MINUTE_COUNTS;
	private final TimedCounter[] counterArray = new TimedCounter[NUMBER_OF_MINUTE_COUNTS];
	private int counterArrayIndex = 0;

	public HistoricEventCounter() {
		for (int i = 0; i < NUMBER_OF_MINUTE_COUNTS; i++) {
			counterArray[i] = new TimedCounter();
		}
	}

	public synchronized void increment() {
		long currentTime = System.currentTimeMillis();

		for (int i = 0; i < NUMBER_OF_MINUTE_COUNTS; i++) {
			TimedCounter timedCounter = counterArray[i];

			if ((currentTime - timedCounter.getStartTime()) > PERIOD_MILLISECONDS) {
				timedCounter.reset();
				counterArrayIndex = (i == (NUMBER_OF_MINUTE_COUNTS - 1)) ? 0 : i + 1;
			}

			timedCounter.increment();
		}
	}

	public synchronized long getCount() {
		return counterArray[counterArrayIndex].getCount();
	}

}
