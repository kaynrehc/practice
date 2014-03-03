import java.util.Date;

/**
 * User: mchernyak
 * Date: 3/2/14
 * Time: 12:34 PM
 */
public class HistoricEventCounter {
    private static final int NUMBER_OF_MINUTE_COUNTS = 3;
    private static final long PERIOD_MILLISECONDS = 1000 * 60 * NUMBER_OF_MINUTE_COUNTS;
    private static final long ONE_MINUTE_MILLISECONDS = 1000 * 60;
    private final TimedCounter[] counterArray = new TimedCounter[NUMBER_OF_MINUTE_COUNTS];
    private int counterArrayIndex = 0;

    public HistoricEventCounter() {
        for (int i = 0; i < NUMBER_OF_MINUTE_COUNTS; i++) {
            counterArray[i] = new TimedCounter();
        }
    }

    public synchronized void increment() {
        long currentTime = System.currentTimeMillis();
        TimedCounter currentTimedCounter = counterArray[counterArrayIndex];

        if ((currentTime - currentTimedCounter.getStartTime()) > ONE_MINUTE_MILLISECONDS) {

            counterArrayIndex = (counterArrayIndex == (NUMBER_OF_MINUTE_COUNTS - 1)) ? 0 : counterArrayIndex + 1;
            currentTimedCounter = counterArray[counterArrayIndex];

            currentTimedCounter.reset();
        }

        currentTimedCounter.increment();

        for (int i = 0; i < counterArray.length; i++) {
            System.out.print("   " + counterArray[i].getCount());
        }
        System.out.println("      " + new Date() + " " + counterArrayIndex);

    }

    public synchronized long getCount() {
        long rv = 0;
        for (int i = 0; i < counterArray.length; i++) {
            rv += counterArray[i].getCount();
        }
        return rv;
    }

}
