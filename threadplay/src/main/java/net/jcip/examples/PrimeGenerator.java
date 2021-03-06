package net.jcip.examples;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

import net.jcip.annotations.*;

/**
 * PrimeGenerator
 * <p/>
 * Using a volatile field to hold cancellation state
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
	private static ExecutorService exec = Executors.newCachedThreadPool();

	@GuardedBy("this")
	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	private volatile boolean cancelled;

	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}

	static List<BigInteger> generatePrimes(long milli) throws InterruptedException {
		PrimeGenerator generator = new PrimeGenerator();
		exec.execute(generator);
		try {
			MILLISECONDS.sleep(milli);
		} finally {
			generator.cancel();
		}
		return generator.get();
	}

	static void printSomePrimes(int milli) {
		try {
			List<BigInteger> primeList = PrimeGenerator.generatePrimes(milli);
			System.out.print("**** [" + primeList.size() + "]");
			for (int i=primeList.size()-3; i<primeList.size(); i++) {
				System.out.print(" " + primeList.get(i));
			}
			System.out.println(" *****");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PrimeGenerator.printSomePrimes(100);
		PrimeGenerator.printSomePrimes(1000);
		PrimeGenerator.printSomePrimes(5000);
		PrimeGenerator.printSomePrimes(10000);
		System.out.println("***** main() stops");
	}
}
