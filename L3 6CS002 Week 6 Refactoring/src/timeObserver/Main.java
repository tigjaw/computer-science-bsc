package timeObserver;

public class Main {

  public static void main(String[] args) {
    Timer timer = new Timer();
    TextBasedObserver observer = new TextBasedObserver();
    timer.attach(observer);
    
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    timer.stop();    
  }
}
