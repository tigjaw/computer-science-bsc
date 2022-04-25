import java.rmi.*;

public interface CalculationServerInterface extends Remote {
  public int add(int a, int b) throws RemoteException;
  public int takeAway(int a, int b) throws RemoteException;
  public int times(int a, int b) throws RemoteException;
  public int shareBy(int a, int b) throws RemoteException;
}
