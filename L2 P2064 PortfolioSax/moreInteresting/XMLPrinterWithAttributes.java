package moreInteresting;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLPrinterWithAttributes {

  private boolean tagFound     = false;
  private boolean tagSpecified = false;
  private String  tagName;

  public static void main(String[] args) throws Exception {
    new XMLPrinterWithAttributes(args.length == 1 ? args[0] : "bbc1.xml");
  }

  public XMLPrinterWithAttributes(String xmlFile) throws SAXException,
      IOException {
    XMLReader parser = XMLReaderFactory.createXMLReader();
    parser.setContentHandler(new XMLHandler());
    parser.parse(xmlFile);
  }

  /**
   * User may search this XML file for specific tags.
   * This constructor's method is only used to read XML files from
   * the 'logs' package.
   * @param xmlFile
   * @param tag - ERROR, INFO, OUTPUT, RECIEVED, REQUESTED or RESPONSE.
   * @throws SAXException
   * @throws IOException
   */
  public XMLPrinterWithAttributes(String xmlFile, XMLLogger.Tag tag)
      throws SAXException, IOException {
    tagSpecified = true;
    switch (tag) {
    case ERROR:
      this.tagName = "ERROR";
      break;
    case INFO:
      this.tagName = "INFO";
      break;
    case OUTPUT:
      this.tagName = "OUTPUT";
      break;
    case RECIEVED:
      this.tagName = "RECIEVED";
      break;
    case REQUESTED:
      this.tagName = "REQUESTED";
      break;
    case RESPONSE:
      this.tagName = "RESPONSE";
      break;
    default:
      tagSpecified = false;
    }
    XMLReader parser = XMLReaderFactory.createXMLReader();
    parser.setContentHandler(new XMLHandler());
    parser.parse("logs//" + xmlFile);
  }

  class XMLHandler extends DefaultHandler {

    public void startDocument() throws SAXException {
      super.startDocument();
      System.out.println("Start of document");
    }

    /**
     * Called when the end of the document is found.
     */
    public void endDocument() throws SAXException {
      super.endDocument();
      System.out.println("End of document");
    }

    /**
     * Called when a start tag is found
     */
    public void startElement(String uri, String localName, String name,
        Attributes attributes) throws SAXException {
      super.startElement(uri, localName, name, attributes);
      if (tagSpecified) {
        if (name.equalsIgnoreCase(tagName)) {
          System.out.println("<" + name + attributesToString(attributes) + ">");
          tagFound = true;
        }
      } else {
        System.out.println("<" + name + attributesToString(attributes) + ">");
        tagFound = true;
      }
    }

    /**
     * Ignore white space (i.e. cut it out)
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    public void ignoreableWhiteSpace(char[] ch, int start, int length)
        throws SAXException {
      super.ignorableWhitespace(ch, start, length);
    }

    /**
     * Converts an Attributes object (consisting of name-value pairs of XML tag
     * attributes) to a string representation.
     * @param attributes
     * @return a string representation of the given Attributes object.
     */

    private String attributesToString(Attributes attributes) {
      int numberOfAttributes = attributes.getLength();
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < numberOfAttributes; i++) {
        buffer.append(" ");
        String name = attributes.getQName(i);
        String value = attributes.getValue(i);
        buffer.append(name + "=" + value);
        if (i != (numberOfAttributes - 1)) {
          buffer.append(",");
        }
      }
      return buffer.toString();
    }

    /**
     * Called when an end tag is found.
     */
    public void endElement(String uri, String localName, String name)
        throws SAXException {
      super.endElement(uri, localName, name);
      if (tagSpecified) {
        if (tagFound) {
          System.out.println("</" + name + ">\n");
          tagFound = false;
        }
      } else {
        System.out.println("</" + name + ">\n");
        tagFound = false;
      }
    }

    /**
     * Called when character data is found
     */

    public void characters(char[] ch, int start, int length) {
      String text = new String(ch, start, length);
      if (tagSpecified) {
        if (tagFound) {
          System.out.println(text);
        }
      } else {
        System.out.println(text);
      }
    }
  }
}