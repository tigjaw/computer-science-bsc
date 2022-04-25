package minimal;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Retrieves an RSS feed (xml file) either from an
 * url or via the local file system and parses it
 * via DOM (Dom.java).
 * @author Woody
 */
public class DataRetriever {

  public static void main(String[] args) throws Exception {
    if (args.length > 0) {
      //**** if arguments input via command prompt print info.
      System.out.println("Number of Arguements: " + args.length);
      System.out.print("Downloads a document from the specified URL and ");
      System.out.println("prints it to stdout via DOM. Parameters are: ");
      System.out.println("  1) URL" + args[0]);
      
      // **** download file attributes with first argument.
      new DataRetriever().downloadFile(args[0]);
      System.exit(0);
    } else {
      /* **** Example Feeds: ****
       http://rss.ent.yahoo.com/movies/boxoffice.xml
       http://rss.ent.yahoo.com/movies/movie_headlines.xml
       http://www.shropshirestar.com/archive/sport/cricket/feed/
       http://rss.instylemagazine.co.uk/web/instyleuk/rss/parties/thisjustin/index.xml
       http://syndication.digitalspy.co.uk/rss_news/dsbigbrother9.xml
       http://syndication.digitalspy.co.uk/rss_news/dssoaps.xml
       http://syndication.digitalspy.co.uk/rss_news/dscelebrity.xml
      */
      String feed = "";
      try {
        //**** Get input from dialog and put into feed string.
        feed = JOptionPane.showInputDialog("RSS Feed to Find: ", null);
        //**** parse and print XML with attributes with feed String.
        new DataRetriever().downloadFile(feed);
        //XMLPrinterWithAttributes xml = new XMLPrinterWithAttributes(feed);
      } catch (Exception e) {
        //**** XML parsing failed, print error information.
        System.out.println("XML Parsing Failed, ensure URL exists and is formatted correctly.");
      }
    }
  }

  /**
   * Downloads a document from the specified URL and prints it out.
   * If URL appears to be path for local file
   * run tryPathLocally(String path).
   * @param urlText Location to download from.
   * @throws IOException 
   */
  public void downloadFile(String urlText) {
    URL url = null;
    try {
      // **** Creates new URL using method parameter.
      url = new URL(urlText);
    } catch (MalformedURLException e) {
      // **** Error was detected in URL.
      System.out.println("Malformed url, please check file path input. Path may be a local file.");
    }
    if (url != null) {
      // **** If URL creation was successful.
      URLConnection connection = null;
      BufferedReader reader = null;
      try {
        // **** Connect to URL.
        connection = url.openConnection();
        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        /* ****
         * Type a name to save the RSS feed as, the .XML
         * extension is added automatically at the end
         * of the input.
         */
        String fileName = JOptionPane.showInputDialog("" +
            "Save Feed as (file extension .XML is added automatically): ", null) + ".xml";
        System.out.println("file-Name = " + fileName);;
        // **** Open FileWriter to write to this new file.
        FileWriter feedToFile = new FileWriter("data//" + fileName);
        String inputLine;
        /* **** 
         * Loop through downloaded file and append
         * each new line to the newly created file.
         */
        while ((inputLine = reader.readLine()) != null) {
          //System.out.println(inputLine);
          feedToFile.write(inputLine);
        }
        // **** Close IO streams.
        feedToFile.close();
        reader.close();
        try {
          // **** Parse the newly created xml file using Dom.
          new Dom("data//" + fileName);
        } catch (SAXException e) {
          // **** SAX Exception caught through execution of Dom.
          System.out.println("Error running " + fileName + " via Dom.");
        } catch (ParserConfigurationException e) {
          // **** Parser Exception caught through execution of Dom.
          System.out.println("Error parsing " + fileName + " via Dom.");
        }
      } catch (IOException e) { // **** File IO exceptions caught
        // **** Check if path typed was intended for a local file.
        tryPathLocally(urlText);
      }
    } else { // **** END if (url != null)
      // **** Check if path typed was intended for a local file.
      tryPathLocally(urlText);
    }

  }
  
  /**
   * If all attempts at downloading the RSS feed using the URL
   * fails this method is called to check if the URL typed
   * was the path name for a local file. If it is, the file
   * is parsed via Dom.
   * @param path
   */
  public void tryPathLocally(String path) {
    try {
      // **** Parse file from path via Dom.
      new Dom("data//" + path);
    } catch (Exception e) {
      // **** File in path does not exist.
      System.out.println("Path does not exist as an URL or file on local system.\n" +
          "\tPlease check file path input.");
    }
  }
}