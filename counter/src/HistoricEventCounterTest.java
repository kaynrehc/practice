import java.util.Date;

/**
 * User: mchernyak
 * Date: 3/2/14
 * Time: 1:05 PM
 */
public class HistoricEventCounterTest implements Runnable {
    private HistoricEventCounter historicEventCounter;
    private int nThreads = 2;

    public static void main(String[] args) {
        (new HistoricEventCounterTest()).test();
    }

    public void test() {
        historicEventCounter = new HistoricEventCounter();
        for (int i = 0; i < nThreads; i++) {
            (new Thread(this)).start();
        }
    }

    @Override
    public void run() {
        long currentTime = System.currentTimeMillis();
        long stopTime = currentTime + 1000 * 60 * 6;

        while (currentTime < stopTime) {
            historicEventCounter.increment();

            System.out.println(Thread.currentThread().getName() + " " +
                    historicEventCounter.getCount() + " " + new Date());

            try {
                Thread.sleep((long) (Math.random() * 15000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentTime = System.currentTimeMillis();

        }
    }
}
