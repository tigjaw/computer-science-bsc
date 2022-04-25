package sockets;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 * Continuously sends messages via UDP to port 8888 via a loop.
 * @author Joshua Woodyatt
 */
public class Q2_UDP_Sender {

  public static void main(String[] args) throws UnknownHostException {
    
    InetAddress address = InetAddress.getLocalHost();
    byte[] message = null;
    int port = 8888;

    while (true) {
      String line = JOptionPane.showInputDialog(
          "Message to Send (click cancel to end program): ", null);
      if (line == null) { // if nothing input
        System.exit(0);
      }
      
      DatagramSocket socket;
      DatagramPacket packet;
      
      try {
        // catch any exceptions in message.
        message = line.getBytes();
      } catch (Exception e) {
        System.out.println("invalid message input");
      }
      
      // create packet using message.
      packet = new DatagramPacket(message, message.length, address, port);
      try {
        // send packet to socket.
        socket = new DatagramSocket();
        socket.send(packet);
        socket.close();
      } catch (IOException e) {
        System.out.println("Error initialising Socket");
        e.printStackTrace();
      }
      
    } // while (true)
    
  } // main method
}