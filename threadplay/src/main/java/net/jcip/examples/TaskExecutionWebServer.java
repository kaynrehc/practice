package net.jcip.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * TaskExecutionWebServer
 * <p/>
 * Web server using a thread pool
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TaskExecutionWebServer {
	private static final Logger logger = LoggerFactory.getLogger(Preloader.class);
	private static final int NTHREADS = 2;
	private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(9001);

		while (true) {
			final Socket connection = socket.accept();
			exec.execute(new ConnectionHandlerTask(connection));
		}
	}

	private static class ConnectionHandlerTask implements Runnable {
		private final Socket clientConnection;

		private ConnectionHandlerTask(final Socket clientConnection) {
			this.clientConnection = clientConnection;
		}

		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
				PrintWriter out = new PrintWriter(clientConnection.getOutputStream(), true);

				String lineIn = null;
				while ((lineIn = in.readLine()) != null) {
					logger.info("***** in: [{}]", lineIn);
					out.println(lineIn.toUpperCase());
				}

				logger.info("***** closing connection");
				clientConnection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
