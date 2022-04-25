package simpleObserver;

public class ConcreteSubject extends Subject{
  private Object state;
  
  public void setState(final Object state) {
    this.state = state;
  }
  
  public Object getState() {
    return state;
  }
}
