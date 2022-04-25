package dom;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * Loads an address (see address.xsd) from an XML file into a DOM tree then
 * outputs it to stdout. The processTree and processChildren methods
 * demonstrates traversing a DOM tree with recursion.
 * 
 * @author Kevan Buckley
 * @version 1.0, March 2009
 */

public class DomDemo1 {

  int indent = 0;

  public static void main(String[] args) throws Exception {
    new DomDemo1().run();
  }

  public void run() throws Exception {
    String filename = "SampleAddress1.xml";
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(new File(filename));
    processTree(doc);
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

  private void printIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print("  ");
    }
  }

}
