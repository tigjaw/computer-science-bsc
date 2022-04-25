package timeObserver;

public class TextBasedObserver implements Observer {
  public void update(final Subject subject) {
    final Timer timer = (Timer) subject;
    System.out.println(timer.getState() + " seconds elapsed");  
  }
}
