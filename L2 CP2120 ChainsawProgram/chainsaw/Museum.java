package chainsaw;

/**
 * Write a description of class Museum here.
 * 
 * @author Kevan Buckley
 * @version v2.0, February 2008
 */

public class Museum {
  private Chainsaw [ ] chainsaws;
  private int numberOfChainsaws;

  public Museum() {
    initialiseData();
  }
  
  public boolean deleteChainsaw(int id) {
    int thisId;
    int i=0;

    while(i < numberOfChainsaws){ 
      thisId = chainsaws[i].getId();

      if(thisId == id) {
        chainsaws[i].delete();
        return true;
      }
      i++;
    }
    return false;
  }

  public boolean exhibitChainsaw(int id) {
    int thisId;
    int i=0;

    while(i < numberOfChainsaws){ 
      thisId = chainsaws[i].getId();

      if(thisId == id) {
        if(chainsaws[i].getIsDeleted() || chainsaws[i].getIsExhibited())
          return false;
        else {
          chainsaws[i].setIsExhibited(true);
          return true;
        }
      }
      i++;
    }
    return false;
  }
  
  public boolean returnChainsaw(int id) {
    int thisId;
    int i=0;

    while(i < numberOfChainsaws){ 
      thisId = chainsaws[i].getId();

      if(thisId == id) {
        if(chainsaws[i].getIsDeleted() || !chainsaws[i].getIsExhibited())
          return false;
        else {
          chainsaws[i].setIsExhibited(false);
          return true;
        }
      }
      i++;
    }
    return false;
  }

  public boolean addChainsaw(Chainsaw chainsaw){
    if(numberOfChainsaws+1 < chainsaws.length){
      chainsaws[numberOfChainsaws] = chainsaw;
      numberOfChainsaws++;
      return true;
    }
    return false;
  }
  
  // returns all non-deleted chainsaws
  
  public Chainsaw [ ] getAllChainsaws() {
    int [ ] matchingIndices = new int[numberOfChainsaws];
    int nMatches = 0;
    int i;
    
    for(i=0;i<numberOfChainsaws;i++){
      if(!chainsaws[i].getIsDeleted()){
        matchingIndices[nMatches] = i;
        nMatches++;
      }
    }
  
    Chainsaw [ ] results = new Chainsaw[nMatches]; 
    for(i=0;i<nMatches;i++){
      results[i] = chainsaws[matchingIndices[i]];
    }
    
    return results;    
  }
  
  // returns all chainsaws by specified manufacturer
  
  public Chainsaw [ ] getChainsawsByManufacturer(String manufacturer) {
    int [ ] matchingIndices = new int[numberOfChainsaws];
    int nMatches = 0;
    int i;
    
    for(i=0;i<numberOfChainsaws;i++){
      if(chainsaws[i].getManufacturer().equalsIgnoreCase(manufacturer) && !chainsaws[i].getIsDeleted()){
        matchingIndices[nMatches] = i;
        nMatches++;
      }
    }
  
    Chainsaw [ ] results = new Chainsaw[nMatches]; 
    for(i=0;i<nMatches;i++){
      results[i] = chainsaws[matchingIndices[i]];
    }
    
    return results;    
  }

  // returns all chainsaws that are currently on exhibition
  
  public Chainsaw [ ] getChainsawsOnExhibition() {
    int [ ] matchingIndices = new int[numberOfChainsaws];
    int nMatches = 0;
    int i;
    
    for(i=0;i<numberOfChainsaws;i++){
      if(chainsaws[i].getIsExhibited() && !chainsaws[i].getIsDeleted()){
        matchingIndices[nMatches] = i;
        nMatches++;
      }
    }
  
    Chainsaw [ ] results = new Chainsaw[nMatches]; 
    for(i=0;i<nMatches;i++){
      results[i] = chainsaws[matchingIndices[i]];
    }
    
    return results;    
  }
  
  private void initialiseData() {
    chainsaws = new Chainsaw[25];
    numberOfChainsaws = 2;
    chainsaws[0] = new Chainsaw("Bosch", 50, 4.57, true, true, "Yellow", 25.4, false);
    chainsaws[1] = new Chainsaw("Wolf", 120, 6.2, false, false, "Red", 37.5, false);
  }
}
