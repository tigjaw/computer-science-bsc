import java.util.LinkedList;

public class Q2_linkedlist extends Thread {

  /**
   * LinkedList shared between all instances of thread, volatile to maintain
   * atomic incrementing.
   */
  volatile static LinkedList<String> letters;
  volatile static int                counter = 0;

  /** run() contains Thread Execution instructions */
  public void run() {
    letters = new LinkedList<String>();
    int timesMs = (int) (Math.random() * 5000);
    
    /* Block-comment this try block to
     * 'prove' synchronisation and volatility.
     */
    /*
    try {
      sleep(timesMs);
    } catch (InterruptedException e) {
      System.out.print(" - Sleep Failed");
      e.printStackTrace();
    }
    */
    System.out.println("Thread ID is " + this.getId());
    addToList("thread ID = " + this.getId() + ", slept for = " + timesMs);
    printLastAddition();
    System.out.println();

    synchronized (this) {
      if (counter == 4) {
        printAll();
      }
      counter++;
    }
  }

  public synchronized void addToList(String message) {
    letters.add(message);
  }

  public synchronized void printAll() {
    System.out.println("Printing Whole List:\n");
    for (int i = 0; i < letters.size(); i++) {
      System.out.println(letters.get(i));
    }
  }

  public synchronized void printLastAddition() {
    System.out.println("Printing Last Addition to List:");
    System.out.println(letters.getLast());
  }

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      (new Q2_linkedlist()).start();
    }
  } // main method

}