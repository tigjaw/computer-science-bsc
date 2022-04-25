package timeObserver;

public class Timer extends Subject {

  private int secondsElapsed;

  private boolean stopped;

  public Timer() {
    secondsElapsed = 0;

    Thread t = new Thread(new Runnable() {
      public void run() {
        stopped = false;
        while (!stopped) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          secondsElapsed++;
          notifyObservers();
        }
      }
    });
    t.start();
  }

  public void setState(final int state) {
    secondsElapsed = state;
  }

  public int getState() {
    return secondsElapsed;
  }

  public void stop() {
    stopped = true;
  }
}
