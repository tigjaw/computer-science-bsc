package simpleEditor.version2;

public class ExitCommand implements Command {
  public void execute() {
    System.out.println("bye bye");
    System.exit(0);
  }
}
