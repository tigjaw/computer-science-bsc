package askydood00;
import java.io.*;

public class AskyDood {

  enum Orientation {
    NORTH, SOUTH, WEST, EAST;
  }

  enum DrawingStatus {
    NOT_DRAWING, DRAWING;
  }

  private int canvasWidth = 20;

  private int canvasHeight = 20;

  private char[][] canvas;

  private char penCharacter = '#';

  private int penRow = 0;

  private int penColumn = 0;

  private Orientation penOrientation = Orientation.EAST;

  private DrawingStatus drawingStatus = DrawingStatus.NOT_DRAWING;

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
          switch (penOrientation) {
          case SOUTH:
            System.out.print("v");
            break;
          case NORTH:
            System.out.print("^");
            break;
          case WEST:
            System.out.print("<");
            break;
          case EAST:
            System.out.print(">");
            break;
          }
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
        penOrientation = Orientation.NORTH;
      }
      if (line.equals("SOUTH")) {
        penOrientation = Orientation.SOUTH;
      }
      if (line.equals("WEST")) {
        penOrientation = Orientation.WEST;
      }
      if (line.equals("EAST")) {
        penOrientation = Orientation.EAST;
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
    switch (penOrientation) {
    case SOUTH:
      if (penRow < canvasHeight - 1) {
        penRow++;
      }
      break;
    case NORTH:
      if (penRow > 0) {
        penRow--;
      }
      break;
    case EAST:
      if (penColumn < canvasWidth - 1) {
        penColumn++;
      }
      break;
    case WEST:
      if (penColumn > 0) {
        penColumn--;
      }
      break;
    }
    if (drawingStatus == DrawingStatus.DRAWING) {
      canvas[penRow][penColumn] = penCharacter;
    }
  }

  public void save() {
    try {
      PrintWriter out = new PrintWriter(new FileWriter("default.akd"));
      out.println(canvasWidth);
      out.println(canvasHeight);
      out.println(penOrientation);
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
    switch (penOrientation) {
    case SOUTH:
      penOrientation = Orientation.WEST;
      break;
    case NORTH:
      penOrientation = Orientation.EAST;
      break;
    case EAST:
      penOrientation = Orientation.SOUTH;
      break;
    case WEST:
      penOrientation = Orientation.NORTH;
      break;
    }
  }

  public void turnLeft() {
    switch (penOrientation) {
    case SOUTH:
      penOrientation = Orientation.EAST;
      break;
    case NORTH:
      penOrientation = Orientation.WEST;
      break;
    case EAST:
      penOrientation = Orientation.NORTH;
      break;
    case WEST:
      penOrientation = Orientation.SOUTH;
      break;
    }
  }

  public void inputAndChangeDrawingCharacter() {
    penCharacter = Prompt
        .getChar("Please enter the character that you would like to use as the pen");
  }

  public static void main(String[] args) {
    new AskyDood().mainLoop();
  }

}
