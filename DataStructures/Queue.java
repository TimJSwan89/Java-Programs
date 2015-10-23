import java.util.*;
public class Queue {
    LinkedList list;
    public Queue() {
        list = new LinkedList();
    }
    public void add(Object o) {
        list.addFirst(o);
    }
    public Object peek() {
        return list.peekLast();
    }
    public Object remove() {
        return list.removeLast();
    } 
}