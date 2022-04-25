package chainsaw;

// chainsaw
// Powered saws for cutting trees

public class Chainsaw {
  protected String manufacturer; // The name of the manufacturer

  protected int numberOfTeeth; // Number of cutting teeth on the chain

  protected double weight; // Weight in Kg

  protected boolean hasSafetyCutOut; // true if the chainsaw has a safety cut out

  protected boolean isUsed; // True if the chainsaw has been used

  protected String colour; // The main colour of the chainsaw

  protected double cutLength; // The length of the cutting edge in cm

  protected boolean isExhibited; // true if the artifact is on show

  protected boolean isDeleted; // true if the record has been marked for delete

  protected int id; // unique identifier of this record

  protected static int nextId = 0; // Shared throughout class. Stores next free
                                    // id number

  public Chainsaw(String manufacturer, int numberOfTeeth, double weight,
      boolean hasSafetyCutOut, boolean isUsed, String colour,
      double cutLength, boolean isExhibited) {
    this.manufacturer = manufacturer;
    this.numberOfTeeth = numberOfTeeth;
    this.weight = weight;
    this.hasSafetyCutOut = hasSafetyCutOut;
    this.isUsed = isUsed;
    this.colour = colour;
    this.cutLength = cutLength;
    this.isExhibited = isExhibited;
    id = nextId;
    nextId++;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setNumberOfTeeth(int numberOfTeeth) {
    this.numberOfTeeth = numberOfTeeth;
  }

  public int getNumberOfTeeth() {
    return numberOfTeeth;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getWeight() {
    return weight;
  }

  public void setHasSafetyCutOut(boolean hasSafetyCutOut) {
    this.hasSafetyCutOut = hasSafetyCutOut;
  }

  public boolean getHasSafetyCutOut() {
    return hasSafetyCutOut;
  }

  public void setIsUsed(boolean isUsed) {
    this.isUsed = isUsed;
  }

  public boolean getIsUsed() {
    return isUsed;
  }

  public void setColour(String colour) {
    this.colour = colour;
  }

  public String getColour() {
    return colour;
  }

  public void setCutLength(double cutLength) {
    this.cutLength = cutLength;
  }

  public double getCutLength() {
    return cutLength;
  }

  public String toString() {
    String result = "";
    result = result + id + ", ";
    result = result + manufacturer + ", ";
    result = result + numberOfTeeth + ", ";
    result = result + weight + ", ";
    result = result + hasSafetyCutOut + ", ";
    result = result + isUsed + ", ";
    result = result + colour + ", ";
    result = result + cutLength + ", ";
    result = result + isExhibited;
    return result;
  }

  public int getId() {
    return id;
  }

  public boolean getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public void delete() {
    this.isDeleted = true;
  }

  public boolean getIsExhibited() {
    return isExhibited;
  }

  public void setIsExhibited(boolean isExhibited) {
    this.isExhibited = isExhibited;
  }

}
