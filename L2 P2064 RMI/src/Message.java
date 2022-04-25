import java.io.*;

public class Message implements Serializable{
  private String author;
  private String messageText;
  
  public Message(String author, String message) {
    this.author = author;
    this.messageText = message;
  }
     
  public String getAuthor() {
    return author;
  }
  public void setAuthor(String author) {
    this.author = author;
  }
  public String getMessageText() {
    return messageText;
  }
  public void setMessageText(String message) {
    this.messageText = message;
  }
}
