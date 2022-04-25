package question1and2;

public class Q2_Thread extends Thread {

  volatile static int counter = 0;
  /**
   * run() contains Thread Execution instructions
   */
  public void run() {
    int timesMs = (int) (Math.random() * 5000);
    //System.out.println("Thread " + this.getId() + " is sleeping for " + timesMs
    //    + " milliseconds");
    try {
      sleep(timesMs);
    } catch (InterruptedException e) {
      System.out.print(" - Sleep Failed");
      e.printStackTrace();
    }

      counter++;
      System.out.println("Thread ID is " + this.getId() + ", Thread Name is "
          + this.getName() + ", Counter = " + counter);
  }

  /**
   * Main Method.
   * @param args - String[].
   */
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      (new Q2_Thread()).start();
    }
  }

}