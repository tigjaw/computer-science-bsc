import java.rmi.registry.*;

public class Client {
  public static void main(String[] args) {
    String host;
    if (args.length < 1) {
      host = "localhost";
    } else {
      host = args[0];
    }
    try {
      Registry registry = LocateRegistry.getRegistry(host);
      RemoteInterface stub = (RemoteInterface) registry.lookup("Hello");
      String response = stub.sayHello();
      System.out.println("response: " + response);
    } catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
