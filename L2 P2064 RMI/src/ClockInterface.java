
import java.rmi.*;

public interface ClockInterface extends Remote {
  public String getTime() throws RemoteException;
}