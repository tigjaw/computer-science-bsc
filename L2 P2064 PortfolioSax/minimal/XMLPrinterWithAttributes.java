package minimal;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLPrinterWithAttributes {
  private boolean descriptionFound = false;

  public static void main(String[] args) throws Exception {
    new XMLPrinterWithAttributes(args.length == 1 ? args[0] : "bbc1.xml");
  }

  public XMLPrinterWithAttributes(String xmlFile) throws SAXException,
      IOException {
    XMLReader parser = XMLReaderFactory.createXMLReader();
    parser.setContentHandler(new XMLHandler());
    parser.parse(xmlFile);
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
      if(name.equalsIgnoreCase("description")) {
        System.out.println("  <" + name + attributesToString(attributes) + ">");
        descriptionFound = true;
      }
    }

    public void ignoreableWhiteSpace(char[] ch, int start, int length) throws SAXException {
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
      if (descriptionFound) {
      System.out.println("  </" + name + ">\n");
      descriptionFound = false;
      }
    }

    /**
     * Called when character data is found
     */

    public void characters(char[] ch, int start, int length) {
      String text = new String(ch, start, length);
      if (descriptionFound) {
        System.out.println("    " + text);
      }
    }
  }
}