package revision;

public class ArithmeticTrees {

  public static void main(String[] args) {
    new ArithmeticTrees().run();
  }
  
  public void run() {
    Node leaf1 = new LeafNode(7);    
    Node leaf2 = new LeafNode(3);    
    Node leaf3 = new LeafNode(2);    
    Node leaf4 = new LeafNode(5);
    
    Node branch2 = new TimesNode(leaf2, leaf3);
    Node branch1 = new PlusNode(leaf1, branch2);
    Node root = new PlusNode(branch1, leaf4);
    
    int result = root.getValue();
    System.out.println(result);
    
    traverseTree(root);
  }

  int indent = 0;
  private void traverseTree(Node n) {
    if(n instanceof LeafNode) {
      printIndent();
      System.out.println("leaf node with value " + n.getValue());
    } else {
      if(n instanceof InteriorNode){
        InteriorNode in = (InteriorNode) n;
        printIndent();
        System.out.println("interior node");
        indent++;
        printIndent();
        System.out.println("left child");
        indent++;

        traverseTree(in.getLeft());

        indent--;
        
        printIndent();
        System.out.println("right child");
        indent++;
        traverseTree(in.getRight());
        indent--;
        indent--;        
      }
    }    
  }
  
  private void printIndent() {
    for (int i = 0; i < indent; i++) {
      System.out.print("  ");
    }
  }

  abstract class Node {
    abstract public int getValue();
  }
  
  public class LeafNode extends Node {
    private int value;
    
    public LeafNode(int value){
      this.value = value;
    }
    
    public int getValue() {
      return value;
    }    
  }

  abstract class InteriorNode extends Node {
    protected Node left; 
    protected Node right;
    
    public InteriorNode(Node left, Node right){
      this.left = left;
      this.right = right;
    }      
    
    public Node getLeft(){
      return left;
    }

    public Node getRight(){
      return right;
    }
  }
  
  class PlusNode extends InteriorNode {
    public PlusNode(Node left, Node right) {
      super(left, right);
    }
    public int getValue() {
      return left.getValue() + right.getValue();
    }    
  }

  class TimesNode extends InteriorNode {
    public TimesNode(Node left, Node right) {
      super(left, right);
    }
    public int getValue() {
      return left.getValue() * right.getValue();
    }    
  }
}
