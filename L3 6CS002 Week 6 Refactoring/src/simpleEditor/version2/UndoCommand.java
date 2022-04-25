package simpleEditor.version2;

public class UndoCommand implements Command {

  public void execute() {
    System.out.println("undo");
  }
}
