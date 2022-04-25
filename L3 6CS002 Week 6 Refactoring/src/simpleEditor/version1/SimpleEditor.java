package simpleEditor.version1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleEditor implements ActionListener {
  private JFrame frame;
  private JScrollPane contentPane;
  private JEditorPane editor;

  public SimpleEditor() {
    initialiseMainComponenets();
    initialiseMenus();
    frame.setVisible(true);
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
    String command = event.getActionCommand();
    
    if(command.equals("Open")){
      open();
      return;
    }

    if(command.equals("Save")){
      save();
      return;
    }

    if(command.equals("Exit")){
      exit();
    }

    if(command.equals("Copy")){
      copy();
      return;
    }

    if(command.equals("Paste")){
      paste();
      return;
    }

    if(command.equals("Undo")){
      undo();
      return;
    }

    if(command.equals("Redo")){
      redo();
      return;
    }
  }
  
  protected void open() {
    System.out.println("open");
  }
  
  protected void save() {
    System.out.println("save");
  }

  protected void exit() {
    System.out.println("bye bye");
    System.exit(0);
  }
  
  protected void copy() {
    System.out.println("copy");
  }
  
  protected void paste() {
    System.out.println("paste");
  }
  
  protected void undo() {
    System.out.println("undo");
  }
  
  protected void redo() {
    System.out.println("redo");
  }
}
