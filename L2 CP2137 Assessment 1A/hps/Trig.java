package hps;

/**
 * A library of trigonometric functions that operate in degrees as opposed to
 * radians that the standard Math package uses.
 * 
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2009
 */

public class Trig {

  /**
   * Converts a specified angle to the range +/0 180 degrees.
   * 
   * @param angle
   *          angle to be converted
   * @return an equivalent angle to the one supplied, but in the -180 to +180
   *         range
   */

  public static double normaliseAngle(double angle) {
    angle = angle % 360;

    if (angle > 180) {
      angle = angle - 360;
    }
    return angle;
  }

  /**
   * Equivalent to Math.cos, but works in degrees instead of radians.
   * 
   * @param angleInDegrees
   *          angle to find the cosine of
   * @return the cosine of the specified angle
   */
  public static double dcos(double angleInDegrees) {
    double angleInRadians = Math.toRadians(angleInDegrees);
    return Math.cos(angleInRadians);
  }

  /**
   * Equivalent to Math.sin, but works in degrees instead of radians.
   * 
   * @param angleInDegrees angle to find the cosine of
   * @return the sine of the specified angle
   */
  public static double dsin(double angleInDegrees) {
    double angleInRadians = Math.toRadians(angleInDegrees);
    return Math.sin(angleInRadians);
  }

  /**
   * Equivalent to Math.acos, but works in degrees instead of radians.
   * @param cosine the cosine for which the angle is to be calculated
   * @return angle associated with the specified cosine
   */
  public static double dacos(double cosine) {
    double angleInRadians = Math.acos(cosine);
    return Math.toDegrees(angleInRadians);
  }

  /**
   * Equivalent to Math.sin, but works in degrees instead of radians.
   * @param sine the sine for which the angle is to be calculated
   * @return angle associated with the specified sine
   */
  public static double dasin(double sine) {
    double angleInRadians = Math.asin(sine);
    return Math.toDegrees(angleInRadians);
  }

/**
 * Equivalent to Math.atan2, but works in degrees instead of radians.
 * 
 * @param opposite side of the triangle
 * @param adjacent side of the triangle
 * @returns the angle between the hypotenuse and adjacent sides
 */
  public static double datan2(double opposite, double adjacent) {
    double angleInRadians = Math.atan2(opposite, adjacent);
    return Math.toDegrees(angleInRadians);
  }
}
