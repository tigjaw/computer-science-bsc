package timeServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Handler for a single request.
 * @see TimeServer
 */
public class TimeHandler implements Runnable {

  /**
   * Return the current time in the UK locale.
   * @return string containing the current time fore this locale.
   */
  public static final String getTime() {
    return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()
        .getTime());
  }

  /** Socket connection with the client. */
  private final Socket connSock;

  public TimeHandler(Socket connSock) {
    this.connSock = connSock;
  }

  /**
   * Method to handle a single request. Read a request line from the client then
   * respond with a single line showing the current time.
   */
  @Override
  public void run() {
    Logger logger = Logger.getLogger("CP2064");
    try {
      // Get request line from client.
      BufferedReader breader = new BufferedReader(new InputStreamReader(
          this.connSock.getInputStream()));
      String message = breader.readLine();
      logger.info("Received:\n" + message);

      // Send the same response, regardless of the request.
      String response = TimeHandler.getTime();

      DataOutputStream tcpstream = new DataOutputStream(this.connSock
          .getOutputStream());
      tcpstream.writeBytes(response);
      logger.info("Sent response:\n" + response);

      // Close socket.
      this.connSock.close();

    } catch (IOException e) {
      logger.error("Cannot communicate with client.");
    }
  }
}