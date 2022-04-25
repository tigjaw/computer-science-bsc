/**
 * An application to help students with their geometry homework.
 * 
 * @author Kevan Buckley
 * @version 1.1, September 2010
 */

public class TriangleTutor0 {
  public static void main(String[] args) {
    new TriangleTutor0().run();
  }

  public void run() {
    boolean done = false;
    double [] l = new double[3];
    double [] a = new double[3];
    int prec = 3;
    int i;
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
        l[0] = Prompt.getDouble("First side: ");
        l[1] = Prompt.getDouble("Second side: ");
        l[2] = Prompt.getDouble("Third side: ");
        a[0] = Math.acos(((l[1] * l[1]) + (l[2] * l[2]) - (l[0] * l[0]))
            / (2 * l[1] * l[2]));
        a[1] = Math.acos(((l[0] * l[0]) + (l[2] * l[2]) - (l[1] * l[1]))
            / (2 * l[0] * l[2]));
        a[2] = Math.acos(((l[1] * l[1]) + (l[0] * l[0]) - (l[2] * l[2]))
            / (2 * l[1] * l[0]));
        break;
      case 2:
        l[0] = Prompt.getDouble("First side: ");
        l[1] = Prompt.getDouble("Second side: ");
        a[2] = Math.toRadians(Prompt
            .getDouble("Angle (degrees) between first and second side:"));
        l[2] = Math.sqrt((l[0] * l[0]) + (l[1] * l[1])
            - (2 * l[0] * l[1] * Math.cos(a[2])));
        a[0] = Math.acos(((l[1] * l[1]) + (l[2] * l[2]) - (l[0] * l[0]))
            / (2 * l[1] * l[2]));
        a[1] = Math.acos(((l[0] * l[0]) + (l[2] * l[2]) - (l[1] * l[1]))
            / (2 * l[0] * l[2]));
        break;
      case 3:
        prec = Prompt.getInt("Number of decimal places: ");
        break;
      case 4:
        System.out.println("Sides are:");
        for (i = 0; i < l.length; i++) {
          String of = "%1." + prec + "f\n";
          System.out.printf(of, l[i]);
        }
        break;
      case 5:
        System.out.println("Angles are:");
        for (i = 0; i < l.length; i++) {
          String of = "%1." + prec + "f\n";
          System.out.printf(of, Math.toDegrees(a[i]));
        }
        break;
      case 6:
        String format = "%1." + prec + "f\n";
        System.out.print("Length of perimeter=");
        System.out.printf(format, l[0] + l[1] + l[2]);
        break;
      case 7:
        format = "%1." + prec + "f\n";
        System.out.print("Area=");
        System.out.printf(format, (l[0] * l[1] * Math.sin(a[2])) / 2.0);
        break;
      case 8:
        done = true;
        break;
      }
    }
  }
}
