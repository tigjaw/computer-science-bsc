package question4;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * Handler for a single request.
 * @author snim2
 * @version 1.0
 * @see HttpExchange_Server
 */
public class HttpExchange_Handler extends superClass implements Runnable {

  /** Socket connection with the client. */
  private final Socket connSock;

  public HttpExchange_Handler(Socket connSock) {
    this.connSock = connSock;
  }

  /**
   * Method to handle a single request. Read a request line from the client then
   * responds with the appropriate HTML page.
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

      htmlExchange(message, logger);

      // Close socket.
      this.connSock.close();

    } catch (IOException e) {
      logger.error("Cannot communicate with client.");
    }
  }

  public void htmlExchange(String message, Logger logger) throws IOException {
    String[] temp = null;
    /*
     * Splits message when a space is found When first space is found splitting
     * ends Results placed in temp array.
     */
    temp = message.split(" ");
    String getThis = temp[1];
    logger.info("Getting:\n" + message);
    // Need to add double forward slashes for path

    String getThisWithDoubleSlashes = "";
    temp = getThis.split("/");
    for (int i = 1; i < temp.length; i++) {
      if (i == (temp.length - 1)) {
        // do not add slashes after last string in path
        getThisWithDoubleSlashes += temp[i];
      } else {
        getThisWithDoubleSlashes += temp[i] + "//";
      }
    }

    String timeText = new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss")
        .format(new Date().getTime());
    System.out.println("The time/date is : " + timeText);

    File theFile;
    byte[] buffer;
    String responseHeader;
    BufferedInputStream bis;
    byte[] responseInBytes;
    OutputStream os = connSock.getOutputStream();
    try {
      // File theFile = new File ("webpages//index.html");
      theFile = new File("webpages//" + getThisWithDoubleSlashes);
      // give buffer array size of file length
      buffer = new byte[(int) theFile.length()];
      bis = new BufferedInputStream(new FileInputStream(theFile));
      // read file into buffer byte array
      bis.read(buffer);
      // create response header
      responseHeader = "HTTP/1.0 200 OK Date: " + timeText + " GMT Content-Type: text/html Content-Length: "
          + theFile.length() + "\n\n";
      // Concatenate file data data onto end of response header message
      responseHeader = responseHeader.concat(new String(buffer));
      // create byte array with complete response as parameter
      responseInBytes = responseHeader.getBytes();
      // output response
      System.out.println("Sending...");
      os.write(responseInBytes, 0, responseInBytes.length);
      os.flush();
    } catch (Exception e) {
      // output error 404
      String error404 = "<HTML><HEAD><TITLE>Error 404 - Page Not Found</TITLE></HEAD>"
          + "<BODY><H1>Error 404 - Page Not Found</H1><BR>"
          + "<P>The Page you requested was not found.</P>" + "</BODY></HTML>";
      // create response header
      responseHeader = "HTTP/1.0 404 Not Found Date: " + timeText + " GMT Content-Type: text/html Content-Length: "
          + error404.length() + "\n\n";
      // Concatenate file data data onto end of response header message
      responseHeader = responseHeader.concat(new String(error404));
      // create byte array with complete response as parameter
      responseInBytes = responseHeader.getBytes();
      // output error 404 response
      System.out.println("Sending error 404...");
      os.write(responseInBytes, 0, responseInBytes.length);
      os.flush();
    }
  }

}