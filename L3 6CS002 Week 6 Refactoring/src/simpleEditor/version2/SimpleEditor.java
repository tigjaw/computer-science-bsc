package simpleEditor.version2;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class SimpleEditor implements ActionListener {
  private JFrame frame;
  private JScrollPane contentPane;
  private JEditorPane editor;
  private HashMap<String, Command> commands ;

  public SimpleEditor() {
    initialiseMainComponenets();
    initialiseCommands();
    initialiseMenus();
    frame.setVisible(true);
  }

  private void initialiseCommands() {
    commands = new HashMap<String, Command>();
    commands.put("Open", new OpenCommand());
    commands.put("Save", new SaveCommand());
    commands.put("Exit", new ExitCommand());
    commands.put("Copy", new CopyCommand());
    commands.put("Paste", new PasteCommand());
    commands.put("Undo", new UndoCommand());
    commands.put("Redo", new RedoCommand());
  }

  protected void initialiseMainComponenets() {
    frame = new JFrame("Simple Editor");
    editor = new JEditorPane();
    contentPane = new JScrollPane(editor);
    frame.setContentPane(contentPane);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
  }

  protected void initialiseMenus() {
    JMenuBar menuBar = new JMenuBar();
    initialiseFileMenu(menuBar);
    initialiseEditMenu(menuBar);
    frame.setJMenuBar(menuBar);
  }

  private void initialiseFileMenu(final JMenuBar menuBar) {
    JMenu fileMenu = new JMenu("File");
    JMenuItem openMenuItem = new JMenuItem("Open");
    JMenuItem saveMenuItem = new JMenuItem("Save");
    JMenuItem exitMenuItem = new JMenuItem("Exit");

    fileMenu.add(openMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(saveMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(exitMenuItem);

    menuBar.add(fileMenu);

    openMenuItem.addActionListener(this);
    saveMenuItem.addActionListener(this);
    exitMenuItem.addActionListener(this);
  }

  private void initialiseEditMenu(final JMenuBar menuBar) {
    JMenu editMenu = new JMenu("Edit");
    JMenuItem copyMenuItem = new JMenuItem("Copy");
    JMenuItem pasteMenuItem = new JMenuItem("Paste");
    JMenuItem undoMenuItem = new JMenuItem("Undo");
    JMenuItem redoMenuItem = new JMenuItem("Redo");

    editMenu.add(copyMenuItem);
    editMenu.add(pasteMenuItem);
    editMenu.addSeparator();
    editMenu.add(undoMenuItem);
    editMenu.add(redoMenuItem);

    menuBar.add(editMenu);

    editMenu.addActionListener(this);
    copyMenuItem.addActionListener(this);
    pasteMenuItem.addActionListener(this);
    undoMenuItem.addActionListener(this);
    redoMenuItem.addActionListener(this);
  }

  public static void main(String[] args) {
    new SimpleEditor();
  }

  public void actionPerformed(ActionEvent event) {
    String commandString = event.getActionCommand();
    Command command = commands.get(commandString);
    if(command==null){
      System.out.println("Command relating to menu item was not found");
    } else {
      command.execute();
    }
  }
}
