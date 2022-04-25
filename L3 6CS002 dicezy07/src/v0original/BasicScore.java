package v0original;

public class BasicScore {

  protected String comment;
  
  public String getComment() {
    return comment;
  }
  
  public void setComment(String comment) {
    this.comment = comment;
  }
  
  protected void formatComment(){
    comment = comment.replaceAll(" ", "_");
  }
}
