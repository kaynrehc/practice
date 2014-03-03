/**
 * User: mchernyak
 * Date: 3/2/14
 * Time: 12:01 PM
 */
public class TimedCounter {
	private long startTime;
	private long count;

	public TimedCounter() {
		startTime = System.currentTimeMillis();
		count = 0;
	}

	public synchronized long getCount() {
		return count;
	}

	public synchronized void reset() {
		startTime = System.currentTimeMillis();
		count = 0;
	}

	public synchronized void increment() {
		count++;
	}

	public synchronized long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
}
