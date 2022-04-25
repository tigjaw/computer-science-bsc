package sockets;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 * Continuously sends messages via TCP on port 8888 via a loop.
 * @author Joshua Woodyatt
 */
public class Q5_TCP_Sender {

  public static void main(String[] args) throws UnknownHostException,
      IOException {

    while (true) {
      Socket mySocket = new Socket("localhost", 8888);

      // Define output stream
      DataOutputStream topStream = new DataOutputStream(mySocket
          .getOutputStream());

      // Message to send as a string
      String message = JOptionPane.showInputDialog(
          "Message to Send (click cancel to end program): ", null);
      if (message == null) { // if nothing input
        System.exit(0);
      }

      // Convert message to bytes
      topStream.writeBytes(message + "\n");

      // Close socket
      mySocket.close();

    } // while (true)

  } // main method
}