package dom;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.*;

/**
 * Loads an address (see address.xsd) from an XML file into a DOM tree then
 * outputs it to stdout. The processTree and processChildren methods
 * demonstrates traversing a DOM tree with recursion.
 * 
 * @author Kevan Buckley
 * @version 1.0, March 2009
 */

public class DomDemo2 {

  int indent = 0;

  int fatalErrors = 0;

  int errors = 0;

  int warnings = 0;

  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  public static void main(String[] args) throws Exception {
    new DomDemo2().run();
  }

  public void run() throws Exception {
    String filename = "SampleAddress1.xml";
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setValidating(true);
    dbf.setNamespaceAware(true);
    dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

    File schemaFile = new File("address.xsd");
    if (!schemaFile.exists()) {
      System.out.println("schema not found");
      System.exit(1);
    }

    dbf.setAttribute(JAXP_SCHEMA_SOURCE, schemaFile);
    DocumentBuilder db = dbf.newDocumentBuilder();
    db.setErrorHandler(new XMLErrorHandler());
    Document doc = db.parse(new File(filename));

    int totalProblems = fatalErrors + errors + warnings;
    if (totalProblems > 0) {
      System.out.println("fatal errors = " + fatalErrors);
      System.out.println("errors = " + errors);
      System.out.println("warnings = " + warnings);
    } else {
      System.out.println();
      processTree(doc);
    }
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

  class XMLErrorHandler implements ErrorHandler {
    public void error(SAXParseException exception) throws SAXException {
      errors++;
      System.out.println("error: " + exception);
    }

    public void fatalError(SAXParseException exception) throws SAXException {
      fatalErrors++;
      System.out.println("fatal error: " + exception);
    }

    public void warning(SAXParseException exception) throws SAXException {
      warnings++;
      System.out.println("warning: " + exception);
    }

  }
}
