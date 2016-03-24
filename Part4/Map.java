public class Map extends Element { //works similar to Sequence
  private Pair p;
  public Map next;
  private Map first;
  private int length;
  public int dummy;
  
  public Map() {
    first = this;
    p = null;
    next = null;
    length = 0;
    dummy = 0;
  }
  
  public Map(int i) {
    first = this;
    next = null;
    p = null;
    length = 0;
    dummy = i;
  }  

  public void Set(Pair pr) {
    p = pr;
  }
    
  public Pair Get() {
    return p;
  }    
  
  public int length() {
    Map temp = first;
    int count = 0;
    while(temp != null) {
      count++;
      temp = temp.next;
    }
    return count;
  }
  
  public void add(Pair pr) {
  	int end = 0;
  	if(p == null) //first pair
  		addFront(pr);
    
  	else if (pr.getKey() < first.p.getKey()) //pair with smallest key
      addFront(pr);
    
    else {
      Map newM = new Map();
      Map curr;
      newM.Set(pr);
      MapIterator it = this.find(new MyChar(pr.getKey()));
      if(!it.equal(this.end())) { //duplicate key
      	curr = it.getMap();
      	while(pr.getKey() == curr.p.getKey()) {
      		if(curr.next != null)
      			curr = curr.next;
      		else { //insert at back
          	Map temp = curr.next;
          	curr.next = newM;
          	newM.next = temp;
          	end = 1;
      			break;
      		}
      	}
      }
      else {
      	curr = first;
      	while(pr.getKey() > curr.p.getKey()) { //get insert position
      		if(curr.next != null)
      			curr = curr.next;
      		else { //insert at back
          	Map temp = curr.next;
          	curr.next = newM;
          	newM.next = temp;
          	end = 1;
      			break;
      		}
      	}
      }
      if(end == 0) { //insert at front of curr
      	newM.Set(curr.p);
      	curr.p = pr;
      	newM.next = curr.next;
      	curr.next = newM;
      }
      end = 0;
    }
    length++;
  }

  private void addFront(Pair pr) {
    if(length == 0)
      p = pr;
    else {  
      Map newM = new Map();
      newM.Set(p);
      p = pr;
      newM.next = this.next;
      this.next = newM;
    }
  }   

  public void delete(int pos) {
    if(pos >= length || pos < 0) {
      System.out.println("Index out of bounds");
      System.exit(1);
    }
    if (pos == 0) {
      first = first.next;
    }
    else if(pos == length - 1) {
      Map curr = first;
      for(int i = 0; i < length - 2; i++) {
        curr = curr.next;
      }
      curr.next = null;
    }
    else {
      Map curr = first;
      for(int i = 0; i < pos - 1; i++) {
        curr = curr.next;
      }
      curr.next = curr.next.next;
    }
    length--;
  }

  public void Print() {
    Map temp = first;
    System.out.print("[ ");
    while(temp != null) {
    	if(temp.Get() != null)
      temp.Get().Print();
      System.out.print(" ");
      temp = temp.next;
    }
    System.out.print("]");
  }
    
  public Pair first() {
    return first.Get();
  }
    
  public Map rest() {
    if(first.next != null) {
      return first.next;
    }
    else
      return null;
  }    
    
  public Pair index(int pos) {
    Map temp = first;
    if(pos > length || pos < 0) {
      System.out.println("Index out of bounds.");
      System.exit(1);
    }
    for(int i = 0; i < pos; i++) {
      temp = temp.next;
    }
    
    return temp.Get();
  }

  public MapIterator begin() {
    MapIterator it = new MapIterator(this.first);
    return it;
  }
  
  public MapIterator end() {
    Map temp = first;
    Map dummy = new Map(1);
    while(temp != null) {
      temp = temp.next;
    }
    temp = dummy;
    MapIterator it = new MapIterator(temp);
    return it;
  }
  
  public MapIterator find(MyChar key) {
  	Map temp = first;
  	Map found = null;
  	for(int i = 0; i < length; i++) {
  	  if(temp.p.getKey() == key.Get()) {
  	 	  found = temp;
  			break;
  		}
  		temp = temp.next;
  	}
    if(found == null) //no Pair found
    	return this.end();
    else {
    	MapIterator it = new MapIterator(found); 
    	return it;
    }
  }  
   
}
