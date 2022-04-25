package question1and2;

public class Q1_Thread extends Thread {

  /**
   * run() contains Thread Execution instructions
   */
  public void run() {
    int timesMs = (int) (Math.random() * 5000);
    System.out.println("Thread " + this.getId() + " is sleeping for " + timesMs
        + " milliseconds");
    try {
      sleep(timesMs);
    } catch (InterruptedException e) {
      System.out.print(" - Sleep Failed");
      e.printStackTrace();
    }

    System.out.println("Thread ID is " + this.getId() + ", Thread Name is "
        + this.getName());
  }

  /**
   * Main Method.
   * @param args - String[].
   */
  public static void main(String[] args) {
    for (int i = 0; i < 2; i++) {
      (new Q1_Thread()).start();
    }
  }

}