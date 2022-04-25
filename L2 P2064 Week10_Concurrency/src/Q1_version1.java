public class Q1_version1 extends Thread {

  /**
   * counter shared between all instances of thread,
   * volatile to maintain atomic incrementing.
   */
  volatile static int counter = 0;

  /** run() contains Thread Execution instructions */
  public void run() {
    int timesMs = (int) (Math.random() * 5000);
    
    try {
      sleep(timesMs);
    } catch (InterruptedException e) {
      System.out.print(" - Sleep Failed");
      e.printStackTrace();
    }

    synchronized (this) {
      counter++;
      System.out.println("Thread ID is " + this.getId() + ", Thread Name is "
          + this.getName() + ", Counter = " + counter);
    }

  } // run() method

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      (new Q1_version1()).start();
    }
  } // main method

}