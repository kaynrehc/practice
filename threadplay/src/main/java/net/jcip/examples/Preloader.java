package net.jcip.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Preloader {
	private static final Logger logger = LoggerFactory.getLogger(Preloader.class);
	private final ProductInfoCreator productInfoCreator = new ProductInfoCreator();
	private final FutureTask<ProductInfo> productInfoFutureTask = new FutureTask<ProductInfo>(productInfoCreator);

	/**
	 * A Callable implementation, this class's call() method creates a new ProductInfo object.
	 */
	private class ProductInfoCreator implements Callable<ProductInfo> {
		public ProductInfo call() throws DataLoadException {
			logger.info("***** ProductInfoCreator.call()");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ProductInfo productInfo = new ProductInfo("[PI_" + System.currentTimeMillis() + "]");
			return productInfo;
		}

		Throwable t = new Throwable();
	}

	public void start() {
		logger.info("***** Preloader.start()");
		new Thread(productInfoFutureTask).start();
	}

	public ProductInfo get() throws DataLoadException, InterruptedException {

		logger.info("***** Preloader.get()");

		try {
			logger.info("***** calling productInfoFutureTask.get()");
			return productInfoFutureTask.get();
		} catch (ExecutionException e) {
			Throwable cause = e.getCause();
			if (cause instanceof DataLoadException)
				throw (DataLoadException) cause;
			else
				throw LaunderThrowable.launderThrowable(cause);
		}
	}

	class ProductInfo {
		String name;

		ProductInfo(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "ProductInfo{" +
					"name='" + name + '\'' +
					'}';
		}
	}

	public static void main(String[] args) {
		logger.info("***** main() start");

		Preloader preloader = new Preloader();
		preloader.start();

		ProductInfo pi = null;
		try {
			pi = preloader.get();
		} catch (DataLoadException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("***** " + pi);
		logger.info("***** main() finish");
	}
}

class DataLoadException extends Exception {
}
