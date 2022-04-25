package revision;

public class Recursion {
  
  public static void main(String[] args) {
    new Recursion().run();
  }

  private void run() {
    System.out.println(times(3,2));
    System.out.println(times(7,3));
  }

  private int times(int i, int j) {
    if(j==0){
      return 0;
    } else {
      return i + times(i, j-1);
    }
  }
}
