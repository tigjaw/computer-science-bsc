package qquestion4withoutLogging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
  
  /**
   * Main method. Initialise Logger and start the server.
   * @param args - String[]
   */
  public static void main(String[] args) {
    try {
      HttpExchange_Server.acceptSock = new ServerSocket(HttpExchange_Server.port);
    } catch (IOException e) {
      System.out.println("Cannot create socket on port " + HttpExchange_Server.port);
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
    while (true) {
      try {
        Socket connSock = HttpExchange_Server.acceptSock.accept();
        Thread handler = new Thread(new HttpExchange_Handler(connSock));
        handler.start();
      } catch (IOException e) {
        System.out.println("run failed");
      }
    } // while (true)
  }

}