package moreInteresting;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;

/**
 * Creates an HTTP Exchange record in a DOM tree via XML then outputs it.
 * @author Joshua Woodyat
 */
public class DomLogger {
  
  private DocumentBuilderFactory dbf;
  private DocumentBuilder db;
  private Document doc;
  private Node root;

  /** Tags for DomLogger for HTTPexchange class. */
  enum Tag {
    RECIEVED, RESPONSE, OUTPUT, REQUESTED, ERROR, INFO
  };
  
  int indent = 0;
  private String fileName;

  /**
   * Main method should never be used.
   * Parameterised constructor is required.
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    //new DomLogger().run();
    System.out.println(
        "DomLogger Program must be called from Q6_TCP_HTTPexchange application.");
  }
  
  /**
   * Parameter logFile contains the name of the logFile.
   * This will be the word "log@" followed by today's date
   * and time.
   * File name is always XML, ".XML" is added at end of string.
   * Calls startDocument() so the user does not have to.
   * XML File is not actually written until program that calls
   * the Logger specifies to do so.
   * @param logFile - input without file extension.
   */
  public DomLogger(String logFile) {
    if (logFile != null && logFile != "") {
      setFileName(logFile +".xml");
      
      dbf = DocumentBuilderFactory.newInstance();
      try {
        db = dbf.newDocumentBuilder();
        doc = db.newDocument();
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      }
      root = doc.createElement("HTTP_Exchange");
      doc.appendChild(root);
    } else {
      System.out.println("Invalid File Name");
      return;
    }
  }
  
  /**
   * Tag and tag message specified by user, depending on Tag choice
   * the appropriate case is run.
   * @param logType (DomLogger.Tag)
   * @param logText (String)
   */
  public void log(Tag logType, String logText) {
      switch (logType) {
        case ERROR:
          Node errorNode = doc.createElement("ERROR");
          root.appendChild(errorNode);
          errorNode.appendChild(doc.createTextNode(logText));
          System.out.println("XML Logging: " + "Wrote: " + "'<ERROR>"+ logText +" </ERROR>\\n' to String");
          break;
        case INFO:
          Node infoNode = doc.createElement("INFO");
          root.appendChild(infoNode);
          infoNode.appendChild(doc.createTextNode(logText));
          System.out.println("XML Logging: " + "Wrote: " + "'<INFO> "+ logText +" </INFO>\\n' to String");
          break;
        case REQUESTED:
          Node requestedNode = doc.createElement("REQUESTED");
          root.appendChild(requestedNode);
          requestedNode.appendChild(doc.createTextNode(logText));
          System.out.println("XML Logging: " + "Wrote: " + "'<REQUESTED> "+ logText +" </REQUESTED>\\n' to String");
          break;
        case RESPONSE:
          Node responseNode = doc.createElement("RESPONSE");
          root.appendChild(responseNode);
          responseNode.appendChild(doc.createTextNode(logText));
          System.out.println("XML Logging: " + "Wrote: " + "'<RESPONSE> "+ logText +" </RESPONSE>\\n' to String");
          break;
        case OUTPUT:
          Node outputNode = doc.createElement("OUTPUT");
          root.appendChild(outputNode);
          outputNode.appendChild(doc.createTextNode(logText));
          System.out.println("XML Logging: " + "Wrote: " + "'<OUTPUT> "+ logText +" </OUTPUT>\\n' to String");
          break;
        case RECIEVED:
          Node recievedNode = doc.createElement("RECIEVED");
          root.appendChild(recievedNode);
          recievedNode.appendChild(doc.createTextNode(logText));
          System.out.println("XML Logging: " + "Wrote: " + "'<RECIEVED> "+ logText +" </RECIEVED>\\n' to String");
          break;
      }

  }
  
  public void endDocument() {
    try {
      writeXmlFile(doc, fileName);
    } catch (Exception e) {
      System.out.println("Writing to file " + fileName + " failed.");
    }
    processTree(doc);
  }

  /**
   * @param fileName the fileName to set
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * @return the fileName
   */
  public String getFileName() {
    return fileName;
  }

  public void writeXmlFile (Document doc, String filename) throws Exception {
    Source source = new DOMSource(doc);

    File file = new File(filename);
    Result result = new StreamResult(file);

    Transformer t = TransformerFactory.newInstance().newTransformer();
    t.transform(source, result);
  }

  private void processTree(Node n) {
    printIndent();

    if (n.getNodeType() == Node.TEXT_NODE) {
      System.out.println(n.getTextContent());
    } else {

      System.out.println("<" + n.getNodeName() + getAttributes(n) + ">");

      processChildren(n);

      printIndent();
      System.out.println("</" + n.getNodeName() + ">");
    }

  }

  private void printIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print("  ");
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

}