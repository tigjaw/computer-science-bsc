package moreInteresting;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class DataRetriever {

  public static void main(String[] args) throws Exception {
    if (args.length > 0) {
      //**** if arguments input via command prompt print info.
      System.out.println("Number of Arguements: " + args.length);
      System.out.print("Downloads a document from the specified URL and ");
      System.out.println("prints it to stdout. Parameters are: ");
      System.out.println("  1) URL");
      try {
        //**** parse and print XML with attributes with first argument.
        @SuppressWarnings("unused")
        XMLPrinterWithAttributes xml = new XMLPrinterWithAttributes(args[0]);
      } catch (Exception e) {
        //**** XML parsing failed, print error information.
        System.out.println("XML Parsing Failed, ensure URL exists and is formatted correctly.");
      }
      System.exit(0);
    } else {
      String feed = "";
      //**** Feed must be an XML file found in logs folder/package. Type xmllog.xml when prompted.
      try {
        //**** Get input from dialog and put into feed string.
        feed = JOptionPane.showInputDialog("XML Log File to Find (e.g. 'xmllog.xml'): ", null);
        //**** parse and print XML file located at value of feed.
        @SuppressWarnings("unused")
        //**** Change value of XMLLogger.Tag.INFO to the tag required.
        XMLPrinterWithAttributes xml = new XMLPrinterWithAttributes(feed, XMLLogger.Tag.INFO);
      } catch (Exception e) {
        //**** XML parsing failed, print error information.
        System.out.println("XML Parsing Failed, ensure URL/file exists and is formatted correctly.");
      }
    }
  }

  /**
   * Downloads a document from the specified URL and prints it out.
   * @param urlText Location to download from.
   */
  public void downloadFile(String urlText) throws Exception {
    URL url = new URL(urlText);
    URLConnection connection = url.openConnection();
    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    String inputLine;
    while ((inputLine = reader.readLine()) != null) {
      System.out.println(inputLine);
    }
    reader.close();
  }
}