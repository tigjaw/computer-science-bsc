package moreInteresting;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * Exchange Server utilising port 8888 to allow requests for
 * HTML pages from a web browser.
 * 
 * @author Joshua Woodyatt
 */
@SuppressWarnings("unused")
public class Q6_TCP_HTTPexchange {

  public static void main(String[] args) throws IOException {
    //DomLogger logWithDom = new DomLogger("logs//" + JOptionPane.showInputDialog("Save log file as (extension .XML is added automatically) : ", null));
    DomLogger logWithDom = new DomLogger("logs//log@" + new SimpleDateFormat("dd-MM-yyyy_HH;mm;ss").format(new Date().getTime()));

    ServerSocket acceptSock = new ServerSocket(8888);
    // Accept a new TCP connection
    Socket connectionSock = acceptSock.accept();
    // Read a stream of data from the connection
    BufferedReader breader = new BufferedReader(new InputStreamReader(
        connectionSock.getInputStream()));
    // Put stream into String
    String message = breader.readLine();
    
    //DomLogger logWithDom = new DomLogger("boo");
    
    String timeText = new SimpleDateFormat("EEE, dd MM yyyy HH:mm:ss").format(new Date().getTime());
    logWithDom.log(DomLogger.Tag.INFO, "Date/Time: " + timeText);
    logWithDom.log(DomLogger.Tag.RECIEVED, message);

    String[] temp = null;
    /*
     * Splits message when a space is found When first space is found splitting
     * ends Results placed in temp array.
     */
    temp = message.split(" ");
    String getThis = temp[1];
    
    logWithDom.log(DomLogger.Tag.REQUESTED, getThis);

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

    OutputStream os = connectionSock.getOutputStream();

    File theFile;
    byte[] buffer;
    String responseHeader;
    BufferedInputStream bis;
    byte[] responseInBytes;
    try {
      // File theFile = new File ("webpages//index.html");
      theFile = new File("webpages//" + getThisWithDoubleSlashes);
      // give buffer array size of file length
      buffer = new byte[(int) theFile.length()];
      bis = new BufferedInputStream(new FileInputStream(theFile));
      // read file into buffer byte array
      bis.read(buffer);
      // create response header
      responseHeader = "HTTP/1.0 200 OK Date: " + timeText
          + " GMT Content-Type: text/html Content-Length: " + theFile.length();
      // logging response header
      logWithDom.log(DomLogger.Tag.RESPONSE, responseHeader);
      responseHeader += "\n\n";
      // Concatenate file data data onto end of response header message
      responseHeader = responseHeader.concat(new String(buffer));
      // create byte array with complete response as parameter
      responseInBytes = responseHeader.getBytes();
      // output response
      System.out.println("Sending...");
      os.write(responseInBytes, 0, responseInBytes.length);
      logWithDom.log(DomLogger.Tag.OUTPUT, getThisWithDoubleSlashes + " Found and Output.");
      os.flush();
      logWithDom.log(DomLogger.Tag.INFO, "Exchange Complete - Successful");
    } catch (Exception e) {
      // log error 404 occurrence
      logWithDom.log(DomLogger.Tag.ERROR, "Error Finding Requested Page, returning error 404.");
      // output error 404
      // get error 404 page
      String error404 = "<HTML><HEAD><TITLE>Error 404 - Page Not Found</TITLE></HEAD>"
        + "<BODY><H1>Error 404 - Page Not Found</H1><BR>"
        + "<P>The Page you requested was not found.</P>"
        + "</BODY></HTML>";
      // create response header
      responseHeader = "HTTP/1.0 404 Not Found Date: " + timeText
          + " GMT Content-Type: text/html Content-Length: " + error404.length();
      logWithDom.log(DomLogger.Tag.RESPONSE, responseHeader);
      responseHeader += "\n\n";
      // Concatenate file data data onto end of response header message
      // responseHeader = responseHeader.concat(new String(buffer));
      responseHeader = responseHeader.concat(new String(error404));
      // create byte array with complete response as parameter
      responseInBytes = responseHeader.getBytes();
      // output error 404 response
      System.out.println("Sending error 404...");
      os.write(responseInBytes, 0, responseInBytes.length);
      logWithDom.log(DomLogger.Tag.OUTPUT, "Error 404 output.");
      os.flush();
      logWithDom.log(DomLogger.Tag.INFO, "Exchange Complete - Unsuccessful");
    }

    // end XML logging
    logWithDom.endDocument();
    
    // Close sockets
    acceptSock.close();
    connectionSock.close();
  }

}