import java.rmi.*;
import java.util.*;

public interface BulletinBoard extends Remote {
  public final static String SERVICE_NAME ="BulletinBoard";
  public void postMessage(Message message) throws RemoteException;
  public int getMessageCount() throws RemoteException;
  public Message[] getAllMessages() throws RemoteException;
}
