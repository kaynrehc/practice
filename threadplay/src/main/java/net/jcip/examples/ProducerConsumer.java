package net.jcip.examples;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.*;

/**
 * ProducerConsumer
 * <p/>
 * Producer and consumer tasks in a desktop search application
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ProducerConsumer {

	static class FileCrawler implements Runnable {

		private final BlockingQueue<File> fileQueue;
		private final FileFilter fileFilter;
		private final File root;

		public FileCrawler(BlockingQueue<File> fileQueue,
						   final FileFilter fileFilter,
						   File root) {
			this.fileQueue = fileQueue;
			this.root = root;
			this.fileFilter = new FileFilter() {
				public boolean accept(File f) {
					return f.isDirectory() || fileFilter.accept(f);
				}
			};
		}

		private boolean alreadyIndexed(File f) {
			return false;
		}

		public void run() {
			// run 60 times
			for (int i = 0; i < 10; i++) {
				try {
					crawl(root);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

				try {
					System.out.println("***** FileCrawler sleeps ******************************");
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void crawl(File root) throws InterruptedException {
			File[] entries = root.listFiles(fileFilter);
			if (entries != null) {
				for (File entry : entries)
					if (entry.isDirectory())
						crawl(entry);
					else if (!alreadyIndexed(entry)) {
						System.out.println("***** inserting into queue: " + entry.getName());
						fileQueue.put(entry);
					}
			}
		}
	}

	static class Indexer implements Runnable {
		private final BlockingQueue<File> queue;

		public Indexer(BlockingQueue<File> queue) {
			this.queue = queue;
		}

		public void run() {
			try {
				while (true) {
					System.out.println("***** trying to take from queue, thread: " + Thread.currentThread().getName());
					File f = queue.take();
					System.out.println("***** took from queue: " + f.getName());
					indexFile(f);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println("***** Indexer run stops.");
		}

		public void indexFile(File file) {
			// Index the file...
		}

	}

	private static final int BOUND = 10;
	private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

	public static void startIndexing(File[] roots) {

		System.out.println("***** startIndexing(), N_CONSUMERS:" + N_CONSUMERS);

		BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
		};

		for (File root : roots)
			new Thread(new FileCrawler(queue, filter, root)).start();

		for (int i = 0; i < N_CONSUMERS; i++)
			new Thread(new Indexer(queue)).start();
	}

	public static void main(String[] agrs) {
		System.out.println("***** main()");
		File[] roots = new File[1];
		roots[0] = new File("/home/mchernyak/src/practice/threadplay/src/test/");

		ProducerConsumer.startIndexing(roots);
	}

}
