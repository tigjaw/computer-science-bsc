package moreInteresting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class XMLLogger {
  
  private FileWriter writeToFile;
  private String dataToWrite = "";
  private boolean logEnded;
  
  /** Tags for XMLLogger for HTTPexchange class. */
  enum Tag {
    RECIEVED, RESPONSE, OUTPUT, REQUESTED, ERROR, INFO
  };
  
  /** XMLLogger main Method.
   * Calls parameterised constructor with first argument
   * ran via command prompt.
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new XMLLogger((args.length == 1 ? args[0] : "log.xml"));
  }

  /**
   * User inputs XML File name to create or to write to.
   * File name is always XML, ".XML" is added at end of string.
   * Calls startDocument() so the user does not have to.
   * @param logFile - input without file extension.
   */
  public XMLLogger(String logFile) {
    try {
      try {
        // if file exists already read it into buffer.
        BufferedReader existingXMLFile = new BufferedReader(new FileReader("logs//" + logFile + ".xml"));
        String line = null;
        while ((line = existingXMLFile.readLine()) != null) {
          // while line is not the end of the file.
          if (!line.equals("</Logging_HTTP_Requests>")) {
            // if line does not equal "</Logging_HTTP_Requests>" append line to
            // dataToWrite.
            dataToWrite += line;
          }
        }
        System.out.println("XML Logging: " + logFile + ".xml exists");
      } catch (IOException e) {
        System.out.println("XML Logging: " + logFile + ".xml does not exist, creating new log file.");
      }
      // Creating or overwriting log XML file.
      writeToFile = new FileWriter("logs//" + logFile + ".xml");
      if (dataToWrite == null || dataToWrite == "") {
        startDocument();
      } else {
        dataToWrite += "\n\n<HTTP_Exchange>\n";
      }
    } catch (IOException e) {
      // if all failed.
      endLogging();
      System.out.println("XML Logging: " + "XML File Creation and Opening Failed, check file name input.");
    }
  }
  
  /**
   * Called automatically by constructor.
   * Writes "<Logging_HTTP_Requests>" to start of file.
   */
  private void startDocument() {
    if (logEnded != true) {
      try {
        // Add XML encoding and XML root <Logging_HTTP_Requests>
        dataToWrite = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<Logging_HTTP_Requests>\n\n";
        // Add sub root <HTTP_Exchange>
        dataToWrite += "<HTTP_Exchange>\n";
      } catch (Exception e) {
        System.out.println("XML Logging: " + "Writing to Log Failed at Start of Document.");
      }
    } else {
      System.out.println("XML Logging: " + "Cannot Write to XML File, logging was ended.");
    }
  }
  
  /**
   * Tag and tag message specified by user, depending on Tag choice
   * the appropriate case is run.
   * @param logType (XMLLogger.Tag)
   * @param logText (String)
   */
  public void log(Tag logType, String logText) {
    if (logEnded != true) {
      switch (logType) {
        case ERROR:
          dataToWrite += "<ERROR> "+ logText +" </ERROR>\n";
          System.out.println("XML Logging: " + "Wrote: " + "'<ERROR>"+ logText +" </ERROR>\\n' to String");
          break;
        case INFO:
          dataToWrite += "<INFO> "+ logText +" </INFO>\n";
          System.out.println("XML Logging: " + "Wrote: " + "'<INFO> "+ logText +" </INFO>\\n' to String");
          break;
        case REQUESTED:
          dataToWrite += "<REQUESTED> "+ logText +" </REQUESTED>\n";
          System.out.println("XML Logging: " + "Wrote: " + "'<REQUESTED> "+ logText +" </REQUESTED>\\n' to String");
          break;
        case RESPONSE:
          dataToWrite += "<RESPONSE> "+ logText +" </RESPONSE>\n";
          System.out.println("XML Logging: " + "Wrote: " + "'<RESPONSE> "+ logText +" </RESPONSE>\\n' to String");
          break;
        case OUTPUT:
          dataToWrite += "<OUTPUT> "+ logText +" </OUTPUT>\n";
          System.out.println("XML Logging: " + "Wrote: " + "'<OUTPUT> "+ logText +" </OUTPUT>\\n' to String");
          break;
        case RECIEVED:
          dataToWrite += "<RECIEVED> "+ logText +" </RECIEVED>\n";
          System.out.println("XML Logging: " + "Wrote: " + "'<RECIEVED> "+ logText +" </RECIEVED>\\n' to String");
          break;
      }
    } else {
      System.out.println("XML Logging: " + "Cannot Write to XML File, logging was ended.");
    }

  }
  
  /**
   * Ends Document.
   */
  public void endDocument() {
    if (logEnded != true) {
      dataToWrite += "</HTTP_Exchange>\n\n</Logging_HTTP_Requests>";
      System.out.println("XML Logging: " + "Wrote: '\\n</Logging_HTTP_Requests>' to String.");
      try {
        writeToFile.write(dataToWrite);
        endLogging();
      } catch (IOException e) {
        System.out.println("XML Logging: " + "Writing to Log Failed at End of Document.");
      }
    } else {
      System.out.println("XML Logging: " + "Cannot Write to XML File, logging was ended.");
    }
  }
  
  /**
   * Closes file.
   * Called automatically by endDocument(). Cannot be otherwise called.
   */
  private void endLogging() {
    try {
      logEnded = true;
      writeToFile.close();
    } catch (IOException e) {
      System.out.println("XML Logging: " + "Cannot Close XML File.");
    }
  }
  
}