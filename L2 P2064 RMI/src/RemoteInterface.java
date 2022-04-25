
import java.rmi.*;

public interface RemoteInterface extends Remote {
  String sayHello() throws RemoteException;
}
