import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class ClockServer implements ClockInterface {
  public ClockServer() {
  }

  public String getTime() {
    long now = System.currentTimeMillis();
    Calendar calendar = new GregorianCalendar();
    calendar.setTimeInMillis(now);
    String time = calendar.get(Calendar.HOUR_OF_DAY) + ":"
        + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    return time;
  }

  public static void main(String args[]) {
    try {
      ClockServer obj = new ClockServer();
      ClockInterface stub = (ClockInterface) UnicastRemoteObject
          .exportObject(obj, 0);
      Registry registry = LocateRegistry.getRegistry();
      registry.bind("Clock", stub);

      System.err.println("Clock server ready");
    } catch (Exception e) {
      System.err.println("Server exception: " + e.toString());
      //e.printStackTrace();
    }
  }
}