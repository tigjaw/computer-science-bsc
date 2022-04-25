import static org.junit.Assert.*;
import org.junit.Test;

public class TestTriangleTutor {

@Test
  public void testSideAccessorMethods(){
    TriangleTutor2 tt = new TriangleTutor2();
    tt.setSideLength(0, 3);
    assertEquals(3, tt.getSideLength(0), 0.01);
  }

@Test
  public void testAngleAccessorMethods(){
    TriangleTutor2 tt = new TriangleTutor2();
    double angle = Math.toRadians(90);
    tt.setAngle(2, angle);
    assertEquals(angle, tt.getAngle(2), 0.01);
    assertEquals(90, tt.getAngleInDegrees(2), 0.01);    
  }

@Test
  public void testCalculateAnglesGivenSideLengths() {
    TriangleTutor2 tt = new TriangleTutor2();
    tt.setSideLength(0, 3);
    tt.setSideLength(1, 4);
    tt.setSideLength(2, 5);
    tt.calculateAnglesGivenSideLengths();
    assertEquals(36.87, tt.getAngleInDegrees(0), 0.01);
    assertEquals(53.13, tt.getAngleInDegrees(1), 0.01);
    assertEquals(90, tt.getAngleInDegrees(2), 0.01);
  }

 @Test
  public void testCalculateRemainingSideAndTwoAngles() {
    TriangleTutor2 tt = new TriangleTutor2();
    tt.setSideLength(0, 3);
    tt.setSideLength(1, 4);
    tt.setAngle(2, Math.toRadians(90));
    tt.calculateRemainingSideAndTwoAngles();
    assertEquals(36.87, tt.getAngleInDegrees(0), 0.01);
    assertEquals(53.13, tt.getAngleInDegrees(1), 0.01);
    assertEquals(90, tt.getAngleInDegrees(2), 0.01);
  }

 @Test
  public void testGetPerimeterLength() {
    TriangleTutor2 tt = new TriangleTutor2();
    tt.setSideLength(0, 3);
    tt.setSideLength(1, 4);
    tt.setSideLength(2, 5);
    tt.calculateAnglesGivenSideLengths();
    assertEquals(12, tt.getPerimeterLength(), 0.01);
  }


 @Test
  public void testGetArea() {
    TriangleTutor2 tt = new TriangleTutor2();
    tt.setSideLength(0, 3);
    tt.setSideLength(1, 4);
    tt.setSideLength(2, 5);
    tt.calculateAnglesGivenSideLengths();
    assertEquals(6, tt.getArea(), 0.01);
  }

}