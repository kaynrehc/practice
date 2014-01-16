package net.jcip.examples;

import java.util.concurrent.*;

public class Preloader {

	private final ProductInfoCreator productInfoCreator = new ProductInfoCreator();
	private final FutureTask<ProductInfo> productInfoFutureTask = new FutureTask<ProductInfo>(productInfoCreator);

	/**
	 * A Callable implementation, this class's call() method creates a new ProductInfo object.
	 */
	private class ProductInfoCreator implements Callable<ProductInfo> {
		public ProductInfo call() throws DataLoadException {
			ProductInfo productInfo = new ProductInfo();
			long milli = 0;
			while ((milli = System.currentTimeMillis()) % 10000 != 0) ;
			productInfo.setName("[PI_" + milli + "]");
			return productInfo;
		}
	}

	public void start() {
		System.out.println("***** Preloader.start()");
		new Thread(productInfoFutureTask).start();
	}

	public ProductInfo get()
			throws DataLoadException, InterruptedException {

		System.out.println("***** Preloader.get()");

		try {
			System.out.println("***** calling productInfoFutureTask.get()");
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

		String getName() {
			return name;
		}

		void setName(String name) {
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
		System.out.println("***** main() start");

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

		System.out.println("***** " + pi);
		System.out.println("***** main() finish");
	}
}

class DataLoadException extends Exception {
}
