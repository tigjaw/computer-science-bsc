package minimal;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

/**
 * Loads a HTTP Exchange log from an XML file into a DOM tree then outputs it to
 * stdout. The processTree and processChildren methods demonstrates traversing a
 * DOM tree with recursion.
 * @author Joshua Woodyatt.
 */
public class Dom {

  int             indent           = 0;
  private boolean descriptionFound = false;

  public static void main(String[] args) throws Exception {
    // new Dom().run();
    new Dom(args.length == 1 ? args[0] : "data//bbc.xml");
  }

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

  private void processTree(Node n) {
    // printIndent();

    if (n.getNodeType() == Node.TEXT_NODE) {
      if (descriptionFound) {
        System.out.println(n.getTextContent());
      }
    } else {
      if (n.getNodeName().equalsIgnoreCase("description")) {
        System.out.println("<" + n.getNodeName() + getAttributes(n) + ">");
        descriptionFound = true;
      } else {
        descriptionFound = false;
      }
      processChildren(n);

      if (descriptionFound == true) {
        // printIndent();
        System.out.println("</" + n.getNodeName() + ">\n");
        descriptionFound = false;
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

  /** Not used in this package */
  @SuppressWarnings("unused")
  private void printIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print("  ");
    }
  }

}