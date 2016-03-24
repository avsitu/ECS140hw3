public class MapIterator {
 private Map m;
 
 public MapIterator() {}
 
 public MapIterator(Map map) {
   m = map;
 }
 
 public boolean equal (MapIterator other) {
   if (m == null && other.m.dummy == 1)
     return true;
   else
     return (this.get() == other.get());
 }
 
 public MapIterator advance() {
   m = m.next;
   return this;
 }
 
 public Map getMap() {
	 return m;
 }
 
 public Pair get() {
   return m.Get();
 }
  

}