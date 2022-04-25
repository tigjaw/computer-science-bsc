import java.rmi.registry.*;
import java.rmi.server.*;

public class Server implements RemoteInterface {
  public Server() {
  }

  public String sayHello() {
    return "Hello, world!";
  }

  public static void main(String args[]) {
    try {
      Server obj = new Server();
      RemoteInterface stub = (RemoteInterface) UnicastRemoteObject
          .exportObject(obj, 0);
      Registry registry = LocateRegistry.getRegistry();
      registry.bind("Hello", stub);

      System.err.println("Server ready");
    } catch (Exception e) {
      System.err.println("Server exception: " + e.toString());
      e.printStackTrace();
    }
  }
}