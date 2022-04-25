import java.rmi.registry.*;

public class ClockClient {
  public static void main(String[] args) {
    String host;
    if (args.length < 1) {
      host = "localhost";
    } else {
      host = args[0];
    }
    try {
      Registry registry = LocateRegistry.getRegistry(host);
      ClockInterface stub = (ClockInterface) registry.lookup("Clock");
      String response = stub.getTime();
      System.out.println("The time on the server is " + response);
    } catch (Exception e) {
      //System.err.println("Client exception: " + e.toString());
      //e.printStackTrace();
    }
  }
}