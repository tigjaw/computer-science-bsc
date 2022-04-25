import java.rmi.registry.*;
import java.rmi.server.*;

public class CalculationServerImplementation implements
    CalculationServerInterface {

  public int add(int a, int b) {
    return a + b;
  }

  public int shareBy(int a, int b) {
    return a / b;
  }

  public int takeAway(int a, int b) {
    return a - b;
  }

  public int times(int a, int b) {
    return a * b;
  }

  public static void main(String[] args) {
    try {
      String name = "Calculations";
      CalculationServerInterface server = new CalculationServerImplementation();
      CalculationServerInterface stub = (CalculationServerInterface) UnicastRemoteObject
          .exportObject(server, 0);
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind(name, stub);
      System.out.println(name + " bound");
    } catch (Exception e) {
      System.err.println("Exception:" + e);
      e.printStackTrace();
    }
  }
}
