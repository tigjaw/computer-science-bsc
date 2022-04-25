package question4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 * Simple threaded server which serves html file requests to all clients on port
 * 8888.
 * @author 0622803
 */
public class HttpExchange_Server implements Runnable {

  /** Port this server runs on. */
  private static final int port = 8888;

  /** Server socket acceptomg incoming connections from browsers. */
  private static ServerSocket acceptSock;

  /** Log4J log file records server interactions. */
  private static Logger logger;
  
  /**
   * Set up Log4J logger for the web server, with name "CP2064".
   * @see HttpExchange_Server
   */
  private static void initialiseLogger() {
    HttpExchange_Server.logger = Logger.getLogger("CP2064");
    PatternLayout sl = new PatternLayout("%-5p %d %l - %m%n");
    String path = "/tmp/CP2064_HttpExchangeServer.log";
    RollingFileAppender fa;
    try {
      fa = new RollingFileAppender(sl, path);
      fa.setMaxFileSize("10MB");
      fa.setMaxBackupIndex(2);
      HttpExchange_Server.logger.addAppender(fa);
    } catch (Exception e) {
      System.err.println("Could not create log file: " + path);
    }
    HttpExchange_Server.logger.info("*** Time server started. ***");
  }
  
  /**
   * Main method. Initialise Logger and start the server.
   * @param args - String[]
   */
  public static void main(String[] args) {
    HttpExchange_Server.initialiseLogger();
    try {
      HttpExchange_Server.acceptSock = new ServerSocket(HttpExchange_Server.port);
    } catch (IOException e) {
      HttpExchange_Server.logger.error("Cannot create socket on port "
          + HttpExchange_Server.port);
    }
    new Thread(new HttpExchange_Server()).start();
  }

  /**
   * Run the HttpExchange server. Continuously accept connections from incoming
   * clients and spawn a new HttpExchange_Handler object to handle each request.
   * 
   * @see HttpExchange_Server
   */
  public void run() {
    Logger logger = Logger.getLogger("CP2064");
    while (true) {
      try {
        Socket connSock = HttpExchange_Server.acceptSock.accept();
        Thread handler = new Thread(new HttpExchange_Handler(connSock));
        handler.start();
        logger.info("Accepted connection from new client.");
      } catch (IOException e) {
        logger.error("Cannot start server on port " + HttpExchange_Server.port);
      }
    }
  }

}