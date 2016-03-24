class Pair extends Element {
  private MyChar key;
  private Element val;

  Pair () {
    key = null;
    val = null;
  }

  Pair (MyChar sym, Element value)
  {
    key = sym;
    val = value;
  }

  public void Set (MyChar sym, Element value) {
    key = sym;
    val = value;
  }

  public Element getVal () {
    return val;
  }
  
  public char getKey () {
    return key.Get();
  }

  public void Print() {
    System.out.print("(");
    key.Print();
    System.out.print(" ");
    val.Print();
    System.out.print(")");
  }

}
    
    
    
    
    