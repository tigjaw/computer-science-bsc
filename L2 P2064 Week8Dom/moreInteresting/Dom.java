package moreInteresting;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

/**
 * Loads a HTTP Exchange log from an XML file into a DOM tree then
 * outputs it to stdout. The processTree and processChildren methods
 * demonstrates traversing a DOM tree with recursion.
 * @author Joshua Woodyatt.
 */
public class Dom {
  /** Tag to print out */
  String exlusiveTag;

  int indent = 0;
  private boolean exlusiveTagFound = false;

  /**
   * Main method should never be used. Dom should only
   * be called via DataRetriever.java.
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    //new Dom().run();
    new Dom(args.length == 1 ? args[0] : "data//bbc.xml");
  }
  
  /**
   * First parameterised constructor, not used in this package.
   * @param xmlFile
   * @throws SAXException
   * @throws ParserConfigurationException
   */
  public Dom(String xmlFile) throws SAXException, ParserConfigurationException {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(xmlFile));
      processTree(doc);
    } catch (IOException e) {
      System.out.println("Error parsing document.");
    }
  }
  
  /**
   * Second  parameterised constructor, allows user to specify
   * a single tag to print to screen.
   * @param xmlFile - file path/name
   * @param tagToShow - name of tag to be printed
   * @throws SAXException
   * @throws ParserConfigurationException
   */
  public Dom(String xmlFile, String tagToShow) throws SAXException, ParserConfigurationException {
    try {
      // tag to display:
      this.exlusiveTag = tagToShow;
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(new File(xmlFile));
      // begin tree processing:
      processTree(doc);
    } catch (IOException e) {
      System.out.println("Error parsing document.");
    }
  }

  /*
  public void run() throws Exception {
    String filename = "SampleAddress1.xml";
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(new File(filename));
    processTree(doc);
  }
  */
  
  private void processTree(Node n) {
    //printIndent();

    if (n.getNodeType() == Node.TEXT_NODE) {
      if(exlusiveTagFound) {
        System.out.println(n.getTextContent());
      }
    } else {
      /*
      System.out.println("<" + n.getNodeName() + getAttributes(n) + ">");
      processChildren(n);
      printIndent();
      System.out.println("</" + n.getNodeName() + ">");
      */
      
      
      if (n.getNodeName().equalsIgnoreCase(exlusiveTag)) {
        System.out.println("<" + n.getNodeName() + getAttributes(n) + ">");
        
        exlusiveTagFound = true;
      } else {
        exlusiveTagFound = false;
      }
      processChildren(n);
      
      if (exlusiveTagFound == true) {
        //printIndent();
        System.out.println("</" + n.getNodeName() + ">\n");
        exlusiveTagFound = false;
      }
      
    }

  }

  private void processChildren(Node n) {
    indent++;
    Node child = n.getFirstChild();
    while (child != null) {
      processTree(child);
      child = child.getNextSibling();
    }
    indent--;
  }

  private String getAttributes(Node n) {
    StringBuffer buffer = new StringBuffer();

    NamedNodeMap m = n.getAttributes();

    if (m == null) {
      return "";
    }
    for (int i = 0; i < m.getLength(); i++) {
      Node a = m.item(i);
      buffer.append(" " + a.getNodeName() + "=" + a.getNodeValue());
    }

    return buffer.toString();
  }

  /** Not used in this package. */
  @SuppressWarnings("unused")
  private void printIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print("  ");
    }
  }

}