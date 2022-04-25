package simpleObserver;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Subject {
  private LinkedList <Observer> observers;
  
  public Subject() {
    observers = new LinkedList<Observer>();
  }
  
  public void attach(final Observer observer){
    observers.add(observer);
  }
  
  public void detach(final Observer observer){
    observers.remove(observer);
  }
  
  protected void notifyObservers() {
    for (Iterator iterator = observers.iterator(); iterator.hasNext();) {
      Observer observer = (Observer) iterator.next();
      observer.update(this);
    }
  }
}
