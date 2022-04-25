package askydood02;

import java.io.*;

public class AskyDood {

  enum DrawingStatus {
    NOT_DRAWING, DRAWING;
  }

  private int canvasWidth = 20;

  private int canvasHeight = 20;

  private char[][] canvas;

  private char penCharacter = '#';

  private int penRow = 0;

  private int penColumn = 0;

  private DrawingStatus drawingStatus = DrawingStatus.NOT_DRAWING;

  OrientationStrategy west = new LeftOrientation();

  OrientationStrategy east = new RightOrientation();

  OrientationStrategy north = new NorthOrientation();

  OrientationStrategy south = new SouthOrientation();

  OrientationStrategy currentOrientation = west;

  public AskyDood() {
    canvas = new char[canvasHeight][canvasWidth];
    for (int r = 0; r < canvasHeight; r++) {
      for (int c = 0; c < canvasWidth; c++) {
        canvas[r][c] = '.';
      }
    }
  }

  public void drawCanvas() {
    for (int r = 0; r < canvasHeight; r++) {
      for (int c = 0; c < canvasWidth; c++) {
        if (r == penRow && c == penColumn) {
          System.out.print(currentOrientation.getCursor());
        } else {
          System.out.print(canvas[r][c]);
        }
      }
      System.out.println();
    }
  }

  public void mainLoop() {
    boolean done = false;
    do {
      drawCanvas();
      char command = Prompt.getChar("");
      switch (command) {
      case ',': {
        turnLeft();
        break;
      }
      case '.': {
        turnRight();
        break;
      }
      case ' ': {
        moveForward();
        break;
      }
      case 's': {
        save();
        break;
      }
      case 'l': {
        load();
        break;
      }
      case 'q': {
        done = true;
        break;
      }
      case 'c': {
        inputAndChangeDrawingCharacter();
        break;
      }
      case 'u': {
        penUp();
        break;
      }
      case 'd': {
        penDown();
        break;
      }
      }
    } while (!done);
  }

  private void penUp() {
    drawingStatus = DrawingStatus.NOT_DRAWING;
  }

  private void penDown() {
    canvas[penRow][penColumn] = penCharacter;
    drawingStatus = DrawingStatus.DRAWING;
  }

  public void load() {
    try {
      canvas = new char[canvasHeight][canvasWidth];
      BufferedReader reader = new BufferedReader(new FileReader("default.akd"));
      String line;
      line = reader.readLine();
      canvasWidth = Integer.parseInt(line);
      line = reader.readLine();
      canvasHeight = Integer.parseInt(line);

      line = reader.readLine();
      if (line.equals("NORTH")) {
        currentOrientation = new NorthOrientation();
      }
      if (line.equals("SOUTH")) {
        currentOrientation = new SouthOrientation();
      }
      if (line.equals("WEST")) {
        currentOrientation = new LeftOrientation();
      }
      if (line.equals("EAST")) {
        currentOrientation = new RightOrientation();
      }

      line = reader.readLine();
      if (line.equals("DRAWING")) {
        drawingStatus = DrawingStatus.DRAWING;
      } else {
        drawingStatus = DrawingStatus.NOT_DRAWING;
      }

      line = reader.readLine();
      penRow = Integer.parseInt(line);

      line = reader.readLine();
      penColumn = Integer.parseInt(line);

      int row = 0;
      while ((line = reader.readLine()) != null) {
        int col = 0;
        for (char c : line.toCharArray()) {
          canvas[row][col++] = c;
        }
        row++;
      }
      reader.close();
    } catch (Exception e) {
      System.out.println("something went wrong: " + e);
    }
  }

  public void moveForward() {
    currentOrientation.moveForward();
    if (drawingStatus == DrawingStatus.DRAWING) {
      canvas[penRow][penColumn] = penCharacter;
    }
  }

  public void save() {
    try {
      PrintWriter out = new PrintWriter(new FileWriter("default.akd"));
      out.println(canvasWidth);
      out.println(canvasHeight);
      out.println(currentOrientation);
      out.println(drawingStatus);
      out.println(penRow);
      out.println(penColumn);
      for (int r = 0; r < canvasHeight; r++) {
        for (int c = 0; c < canvasWidth; c++) {
          out.print(canvas[r][c]);
        }
        out.println();
      }
      out.close();
    } catch (Exception e) {
      System.out.println("something went wrong: " + e);
    }
  }

  public void turnRight() {
    currentOrientation.turnRight();
  }

  public void turnLeft() {
    currentOrientation.turnLeft();
  }

  public void inputAndChangeDrawingCharacter() {
    penCharacter = Prompt
        .getChar("Please enter the character that you would like to use as the pen");
  }

  public static void main(String[] args) {
    new AskyDood().mainLoop();
  }

  abstract public class OrientationStrategy {
    abstract void turnLeft();
    abstract public char getCursor(); 
    abstract void turnRight();
    abstract void moveForward();
  }
  
  public class LeftOrientation extends OrientationStrategy {
    void moveForward() {
      if (penColumn > 0) {
        penColumn--;
      }
    }

    void turnLeft() {
      currentOrientation = south;
    }

    void turnRight() {
      currentOrientation = north;
    }

    public char getCursor() {
      return '<';
    }
    
    public String toString() {
      return "WEST";
    }
  }

  public class RightOrientation extends OrientationStrategy {
    void moveForward() {
      if (penColumn < canvasWidth - 1) {
        penColumn++;
      }
    }

    void turnLeft() {
      currentOrientation = north;
    }

    void turnRight() {
      currentOrientation = south;
    }

    public char getCursor() {
      return '>';
    }
    
    public String toString() {
      return "EAST";
    }
  }

  public class SouthOrientation extends OrientationStrategy {
    void moveForward() {
      if (penRow < canvasHeight - 1) {
        penRow++;
      }
    }

    void turnLeft() {
      currentOrientation = east;
    }

    void turnRight() {
      currentOrientation = west;
    }
    
    public char getCursor() {
      return 'v';
    }

    public String toString() {
      return "SOUTH";
    }
  }

  public class NorthOrientation extends OrientationStrategy {
    void moveForward() {
      if (penRow > 0) {
        penRow--;
      }
    }

    void turnLeft() {
      currentOrientation = west;
    }

    void turnRight() {
      currentOrientation = east;
    }
    
    public char getCursor() {
      return '^';
    }
    
    public String toString() {
      return "NORTH";
    }
  }
}
