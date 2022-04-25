/**
 * An application to help students with their geometry homework.
 * 
 * @author Kevan Buckley
 * @version 1.1, September 2010
 */
public class TriangleTutor1 {
  private double[] sideLengths;
  private double[] angles;

  public static void main(String[] args) {
    new TriangleTutor1().run();
  }

  public TriangleTutor1(){
    sideLengths = new double[3];
    angles = new double[3];
  }

  public void run() {
    boolean done = false;
    int numberOfDecimalPlaces = 3;
    while (!done) {
      System.out.println();
      System.out.println("Triangle Tutor");
      System.out.println();
      System.out.println(" 1. Set side lengths");
      System.out.println(" 2. Set 2 sides and an angle");
      System.out.println(" 3. Set precision of output");
      System.out.println(" 4. Print side lengths");
      System.out.println(" 5. Print angles");
      System.out.println(" 6. Print perimeter");
      System.out.println(" 7. Print area");
      System.out.println(" 8. Quit");
      int selection = Prompt.getInt("Choose an option");
      switch (selection) {
      case 1:
        sideLengths[0] = Prompt.getDouble("First side: ");
        sideLengths[1] = Prompt.getDouble("Second side: ");
        sideLengths[2] = Prompt.getDouble("Third side: ");
        angles[0] = Math
            .acos(((sideLengths[1] * sideLengths[1])
                + (sideLengths[2] * sideLengths[2]) - (sideLengths[0] * sideLengths[0]))
                / (2 * sideLengths[1] * sideLengths[2]));
        angles[1] = Math
            .acos(((sideLengths[0] * sideLengths[0])
                + (sideLengths[2] * sideLengths[2]) - (sideLengths[1] * sideLengths[1]))
                / (2 * sideLengths[0] * sideLengths[2]));
        angles[2] = Math
            .acos(((sideLengths[1] * sideLengths[1])
                + (sideLengths[0] * sideLengths[0]) - (sideLengths[2] * sideLengths[2]))
                / (2 * sideLengths[1] * sideLengths[0]));
        break;
      case 2:
        sideLengths[0] = Prompt.getDouble("First side: ");
        sideLengths[1] = Prompt.getDouble("Second side: ");
        angles[2] = Math.toRadians(Prompt
            .getDouble("Angle (degrees) between first and second side:"));
        sideLengths[2] = Math.sqrt((sideLengths[0] * sideLengths[0])
            + (sideLengths[1] * sideLengths[1])
            - (2 * sideLengths[0] * sideLengths[1] * Math.cos(angles[2])));
        angles[0] = Math
            .acos(((sideLengths[1] * sideLengths[1])
                + (sideLengths[2] * sideLengths[2]) - (sideLengths[0] * sideLengths[0]))
                / (2 * sideLengths[1] * sideLengths[2]));
        angles[1] = Math
            .acos(((sideLengths[0] * sideLengths[0])
                + (sideLengths[2] * sideLengths[2]) - (sideLengths[1] * sideLengths[1]))
                / (2 * sideLengths[0] * sideLengths[2]));
        break;
      case 3:
        numberOfDecimalPlaces = Prompt.getInt("Number of decimal places: ");
        break;
      case 4:
        System.out.println("Sides are:");
        for (int side = 0; side < sideLengths.length; side++) {
          String of = "%1." + numberOfDecimalPlaces + "f\n";
          System.out.printf(of, sideLengths[side]);
        }
        break;
      case 5:
        System.out.println("Angles are:");
        // TODO the loop termination condition below looks peculiar
        for (int angle = 0; angle < sideLengths.length; angle++) {
          String of = "%1." + numberOfDecimalPlaces + "f\n";
          System.out.printf(of, Math.toDegrees(angles[angle]));
        }
        break;
      case 6:
        String format = "%1." + numberOfDecimalPlaces + "f\n";
        System.out.print("Length of perimeter=");
        System.out.printf(format, sideLengths[0] + sideLengths[1]
            + sideLengths[2]);
        break;
      case 7:
        format = "%1." + numberOfDecimalPlaces + "f\n";
        System.out.print("Area=");
        System.out.printf(format, (sideLengths[0] * sideLengths[1] * Math
            .sin(angles[2])) / 2.0);
        break;
      case 8:
        done = true;
        break;
      }
    }
  }
}
