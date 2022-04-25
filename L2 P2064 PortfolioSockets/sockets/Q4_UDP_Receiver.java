package sockets;

import java.io.*;
import java.net.*;

/**
 * Continuously listens for UDP messages.
 * @author Joshua Woodyatt
 */
public class Q4_UDP_Receiver {

  public static void main(String[] args) throws UnknownHostException {

    System.out.println("Program Started");
    while (true) {
      InetAddress address = InetAddress.getLocalHost();
      int port = 8888;
      DatagramSocket socket;

      byte[] buffer = new byte[256];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
          address, port);
      
      try {
        socket = new DatagramSocket(port, address);
        socket.receive(packet);

        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received: " + received);

        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("**** caught socket error ****");
      } // try { catch

    } // while (true)

  } // main method
}