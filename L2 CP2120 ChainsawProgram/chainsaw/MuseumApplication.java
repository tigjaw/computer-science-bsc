package chainsaw;

/**
 * Write a description of class MuseumApplication here.
 * 
 * @author Kevan Buckley
 * @version v2.0, February 2008
 */

public class MuseumApplication {
  private Museum museum;

  public void run() {
    museum = new Museum();
    int option=getOption();
    
    while(option != 0) {
      switch(option) {
        case 1:
          listAllChainsaws();
          break;
        case 2:
          searchByManufacturer();
          break;
        case 3:
          listChainsawsOnExhibition();
          break;
        case 4:
          addChainsaw();
          break;
        case 5:
          deleteChainsaw();
          break;
        case 6:
          exhibitChainsaw();
          break;
        case 7:
          archiveChainsaw();
          break;
      }
      
      option=getOption();
    }
    
    System.out.println("Finished");
  }

  private int getOption() {
    System.out.println();
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~\nWolvesville Chainsaw Museum\n~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println();
    System.out.println("0 - Quit");
    System.out.println();
    System.out.println("1 - List all chainsaws");
    System.out.println("2 - Search by manufacturer");
    System.out.println("3 - List chainsaws on exhibition");
    System.out.println("4 - Add chainsaw");
    System.out.println("5 - Delete chainsaw");
    System.out.println("6 - Exhibit chainsaw");
    System.out.println("7 - Archive chainsaw");
    System.out.println();
    return TextInputPrompt.getInt("Enter your selection: ");
  }

  private void listAllChainsaws() {
    Chainsaw [ ] chainsaws = museum.getAllChainsaws();

    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("All chainsaws");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println();

    if(chainsaws.length == 0) {
      System.out.println("Museum has no chainsaws!");  
    }

    for(int i=0;i<chainsaws.length;i++){
      System.out.println(chainsaws[i].toString());
    }
  }
  
  private void searchByManufacturer() {
    String name = TextInputPrompt.getString("Enter manufacturer: ");
    Chainsaw[ ] chainsaws = museum.getChainsawsByManufacturer(name);

    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("Manufacturer = " + name);
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println();

    if(chainsaws.length == 0) {
      System.out.println("Museum has no chainsaws by that manufacturer");  
    }

    for(int i=0;i< chainsaws.length;i++){
      System.out.println(chainsaws[i].toString());
    }    
  }
  
  private void listChainsawsOnExhibition() {
    Chainsaw [ ] chainsaws = museum.getChainsawsOnExhibition();

    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("Chainsaws on exhibition");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println();

    if(chainsaws.length == 0) {
      System.out.println("No chainsaws on exhibition");  
    }
    
    for(int i=0;i<chainsaws.length;i++){
      System.out.println(chainsaws[i].toString());
    }
  }
  
 
  private void deleteChainsaw() {
    int id = TextInputPrompt.getInt("Enter chainsaw id: ");
    if(museum.deleteChainsaw(id))
      System.out.println("chainsaw deleted");
    else
      System.out.println("could not delete specified chainsaw");
  }
  
 
  private void addChainsaw() {
    String manufacturer = TextInputPrompt.getString("Manufacturer: ");
    int numberOfTeeth = TextInputPrompt.getInt("NumberOfTeeth: ");
    double weight = TextInputPrompt.getDouble("Weight: ");
    boolean hasSafetyCutOut = TextInputPrompt.getBoolean("HasSafetyCutOut: ");
    boolean isUsed = TextInputPrompt.getBoolean("IsUsed: ");
    String colour = TextInputPrompt.getString("Colour: ");
    double cutLength = TextInputPrompt.getDouble("CutLength: ");

    if(museum.addChainsaw(new Chainsaw(manufacturer, numberOfTeeth, weight, hasSafetyCutOut, isUsed, colour, cutLength, false)))
      System.out.println("chainsaw added");
    else
      System.out.println("could not add chainsaw");  
  }
  
 
  private void exhibitChainsaw() {
    int id = TextInputPrompt.getInt("Enter chainsaw id: ");
    if(museum.exhibitChainsaw(id))
      System.out.println("chainsaw exhibited");
    else
      System.out.println("could not exhibit specified chainsaw");
  }
  
 
  private void archiveChainsaw() {
    int id = TextInputPrompt.getInt("Enter chainsaw id: ");
    if(museum.returnChainsaw(id))
      System.out.println("chainsaw archived");
    else
      System.out.println("could not archive specified chainsaw");
  } 

  public static void main(String [] args) {
    new MuseumApplication().run();
  }
}
