package hps;

/**
 * A class that implements this interface may be informed of changes to the
 * simulation model, if it is registered with the model by calling
 * addModelListener.
 * 
 * @author Kevan Buckley
 * @version 1.0, January 2008
 */

public interface ModelListener {
  public void stateChanged();
}
