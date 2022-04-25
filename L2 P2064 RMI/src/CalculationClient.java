import java.rmi.registry.*;

public class CalculationClient {
  public static void main(String[] args) {
    String host;
    if (args.length < 1) {
      host = "localhost";
    } else {
      host = args[0];
    }
    try {
      Registry registry = LocateRegistry.getRegistry(host);
      CalculationServerInterface stub = (CalculationServerInterface) registry.lookup("Calculations");
      int x = 22;
      int y = 3;
      int result = stub.times(x, y);
      System.out.println(x + " times " + y + " = " + result);
    } catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
