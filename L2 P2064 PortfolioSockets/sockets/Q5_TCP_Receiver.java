package sockets;

import java.io.*;
import java.net.*;

/**
 * Continuously listens for messages on post 8888 via TCP.
 * @author Joshua Woodyatt
 */
public class Q5_TCP_Receiver {

  public static void main(String[] args) throws IOException {

    while (true) {
      ServerSocket acceptSock = new ServerSocket(8888);

      // Accept a new TCP connection
      Socket connectionSock = acceptSock.accept();

      // Read a stream of data from the connection
      BufferedReader breader = new BufferedReader(new InputStreamReader(
          connectionSock.getInputStream()));

      // Put stream into String
      String message = breader.readLine();

      // Print stream string
      System.out.println(message);

      // Close sockets
      acceptSock.close();
      connectionSock.close();
    } // while (true)

  } // main method
}