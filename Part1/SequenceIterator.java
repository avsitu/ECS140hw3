public class SequenceIterator {
  private Sequence s;
  
  public SequenceIterator(Sequence seq) {
    s = seq;
  }
  
  public boolean equal (SequenceIterator other) { 
    if(s == null && other.s.dummy == 1) {
      return true;
    }

    else
      return (this.get() == other.get());
  }
  
  public SequenceIterator advance() {
      s = s.next;
    return this;
  }
  
  public Element get() {
    return s.Get();
  }
  
 
}