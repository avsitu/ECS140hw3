public class Sequence extends Element {
  private Element e;
  public Sequence next;
  private Sequence first;
  private int length;
  public int dummy;
  
  public Sequence() {
    first = this;
    next = null;
    length = 0;
    dummy = 0;
  }
  
  public Sequence(int i) {
    first = this;
    next = null;
    length = 0;
    e = new MyInteger(1);
    dummy = i;
  }  

  public void Set(Element val) {
    e = val;
  }
    
  public Element Get() {
    return e;
  }    
  
  public int length() {
    Sequence temp = first;
    int count = 0;
    while(temp != null) {
      count++;
      temp = temp.next;
    }
    return count;
  }
  
  public void add(Element el, int counter) {
    if(counter > length || counter < 0) {
      System.out.println("Position out of bounds (Add)");
      System.exit(1);
    }
    
    if(el == this) //must make new copy!!!
      el = ((Sequence)el).copy();
    
    if (counter == 0) {
      addFront(el);
    }
    
    else {
      Sequence newS = new Sequence();
      newS.Set(el);
      
      Sequence curr = first;
      for(int i = 0; i < counter - 1; i++) {
        curr = curr.next;
      }
      Sequence temp = curr.next;
      curr.next = newS;
      newS.next = temp;
    }
    length++;
  }

  private void addFront(Element el) {
    if(length == 0)
      e = el;
    else {  
      Sequence newS = new Sequence();
      newS.Set(e);
      e = el;
      newS.next = this.next;
      this.next = newS;
    }
  }    

  public void delete(int pos) {
    if(pos >= length || pos < 0) {
      System.out.println("Index out of bounds (Delete)");
      System.exit(1);
    }
    if (pos == 0) {
      first = first.next;
    }
    else if(pos == length - 1) {
      Sequence curr = first;
      for(int i = 0; i < length - 2; i++) {
        curr = curr.next;
      }
      curr.next = null;
    }
    else {
      Sequence curr = first;
      for(int i = 0; i < pos - 1; i++) {
        curr = curr.next;
      }
      curr.next = curr.next.next;
    }
  }

  public void Print() {
    Sequence temp = first;
    System.out.print("[ ");
    while(temp != null) {
    	if(temp.Get() != null)
    		temp.Get().Print();
      System.out.print(" ");
      temp = temp.next;
    }
    System.out.print("]");
  }
    
  public Element first() {
    return first.Get();
  }
    
  public Sequence rest() {
    if(first.next != null) 
      return first.next;
    else
      return null;
  }    
    
  public Element index(int pos) {
    Sequence temp = first;
    if(pos > length || pos < 0) {
      System.out.println("Index out of bounds (Index)");
      System.exit(1);
    }
    for(int i = 0; i < pos; i++) {
      temp = temp.next;
    }
    
    return temp.Get();
  }
    
  public Sequence flatten() {
    Sequence flatField = new Sequence();   //returned sequence
    int fLength = 0;
    Sequence temp = first;
    for(int i = 0; i < length; i++) {
      if(temp.Get() instanceof Sequence) {
        fLength = ((Sequence)(temp.e)).flatten(flatField, fLength); //call flatten and pass sequence to flatten
        temp = temp.next;
      }
      else {
        flatField.add(temp.Get(), fLength);
        temp = temp.next;
        fLength++;
      }
    }
    return flatField;
  }
    
  public int flatten(Sequence flat, int fLength) {
    Sequence temp = first;
    for(int i = 0; i < length; i++) {
      if(temp.Get() instanceof Sequence) {
        fLength = ((Sequence)(temp.e)).flatten(flat, fLength);
        temp = temp.next;
      }
      else {
        flat.add(temp.Get(), fLength);
        temp = temp.next;
        fLength++;
      }
    }
    return fLength;
  }
    
  public Sequence copy() {
    Sequence dCopy = new Sequence();
    Sequence temp = first;
    Element newItem;

    for(int i = 0; i < length; i++) {
      if(temp.Get() instanceof Sequence) { 
        newItem = new Sequence();
        newItem = ((Sequence)temp.Get()).copy();
        temp = temp.next;
      }
      else {
        if(temp.Get() instanceof MyChar){
          newItem = new MyChar(((MyChar)temp.e).Get());
        }
        else {
          newItem = new MyInteger(((MyInteger)temp.e).Get());
        }
        temp = temp.next;
      }
      dCopy.add(newItem, i);       
    }
    return dCopy;
  }

  public SequenceIterator begin() {
    SequenceIterator it = new SequenceIterator(this.first);
    return it;
  }
  
  public SequenceIterator end() {
    Sequence temp = first;
    Sequence dummy = new Sequence(1);
    while(temp != null) {
      temp = temp.next;
    }
    temp = dummy;
    SequenceIterator it = new SequenceIterator(temp);
    return it;
  }
}
