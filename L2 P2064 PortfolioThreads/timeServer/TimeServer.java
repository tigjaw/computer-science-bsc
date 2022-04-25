package timeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * Simple threaded server which reports the current time to all clients on port
 * 8888.
 * 
 * @author snim2
 * @version 1.0
 */
public class TimeServer implements Runnable {

	/**
	 * Port this server runs on. INV: port > 8000
	 */
	private static final int port = 8888;

	/** Server socket accepting incoming connections from browsers. */
	private static ServerSocket acceptSock;

	/** Log4J log file object. */
	private static Logger logger;

	/**
	 * Set up Log4J logger for the web server, with name "CP2064". This code is
	 * taken directly from the lecture slides in Week 3.
	 * 
	 * @see TimeServer.logger
	 */
	private static void initialiseLogger() {
		TimeServer.logger = Logger.getLogger("CP2064");
		PatternLayout sl = new PatternLayout("%-5p %d %l - %m%n");
		String path = "/tmp/CP2064TimeServer.log";
		RollingFileAppender fa;
		try {
			fa = new RollingFileAppender(sl, path);
			fa.setMaxFileSize("10MB");
			fa.setMaxBackupIndex(2);
			TimeServer.logger.addAppender(fa);
		} catch (Exception e) {
			System.err.println("Could not create log file: " + path);
		}
		TimeServer.logger.info("*** Time server started. ***");
	}

	/**
	 * Initialise the logger and start the time server.
	 * 
	 * @param args
	 *            unused.
	 */
	public static void main(String[] args) {
		TimeServer.initialiseLogger();
		try {
			TimeServer.acceptSock = new ServerSocket(TimeServer.port);
		} catch (IOException e) {
			TimeServer.logger.error("Cannot create socket on port "
					+ TimeServer.port);
		}
		new Thread(new TimeServer()).start();
	}

	/**
	 * Run the time server. Continuously accept connections from incoming
	 * clients and spawn a new TimeHandler object to handle each request.
	 * 
	 * @see TimeHandler
	 */
	public void run() {
		Logger logger = Logger.getLogger("CP2064");
		while (true) {
			try {
				Socket connSock = TimeServer.acceptSock.accept();
				Thread handler = new Thread(new TimeHandler(connSock));
				handler.start();
				logger.info("Accepted connection from new client.");
			} catch (IOException e) {
				logger.error("Cannot start server on port " + TimeServer.port);
			}
		}
	}

}
