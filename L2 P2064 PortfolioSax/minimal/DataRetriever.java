package minimal;

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
      /* **** Example Feeds: ****
       http://www.bbc.co.uk/feeds/rss/music/classicpopandrock/latest_releases.xml
       http://www.bbc.co.uk/feeds/rss/music/rockandindie/latest_releases.xml
       http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/uk/rss.xml
       http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/technology/rss.xml
       http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/england/west_midlands/rss.xml
       http://rss.instylemagazine.co.uk/web/instyleuk/rss/parties/thisjustin/index.xml
       http://www.eyefootball.com/rss_news_main.xml
       http://rss.ent.yahoo.com/movies/boxoffice.xml
       http://rss.ent.yahoo.com/movies/movie_headlines.xml
       http://www.shropshirestar.com/archive/sport/cricket/feed/
       http://syndication.digitalspy.co.uk/rss_news/dsbigbrother9.xml
       http://syndication.digitalspy.co.uk/rss_news/dssoaps.xml
       http://syndication.digitalspy.co.uk/rss_news/dscelebrity.xml
      */
      String feed = "";
      try {
        //**** Get input from dialog and put into feed string.
        feed = JOptionPane.showInputDialog("RSS Feed to Find: ", null);
        //**** parse and print XML with attributes with feed String.
        new XMLPrinterWithAttributes(feed);
      } catch (Exception e) {
        //**** XML parsing failed, print error information.
        System.out.println("XML Parsing Failed, ensure URL exists and is formatted correctly.");
      }
    }
  }

  /** NOT USED.
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