import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Item[] q;            // queue elements
    private int N = 0;           // number of elements on queue
    private int first = 1;       // index of first element of queue
    private int last  = 1;       // index of next available slot
    
	public Deque() {
		q = (Item[]) new Object[3];
	}
	
	private void resize(int max, int power) 
	{
		/* нужно определить размер массива 
		 * первые n-элементов резерв для вставки в начало, тело массива, 
		 * завершающие n-элементов резерв для вставки в конец.
		 */
		Item[] temp = (Item[]) new Object[2*max*power];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = N;
    }
	
	public boolean isEmpty() {
		return N==0;
	}

	public int size() {
		// return the number of items on the deque
		return N;
	}
	
	public void addLast(Item item) {
		if (N == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        N++;
	}

	public void addFirst(Item item) {
		
	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        N--;
        first++;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (N > 0 && N == q.length/4) resize(q.length/2); 
        return item;
	}

	public Item removeLast() {
		throw new UnsupportedOperationException("not implemented");
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    } 
	
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item>
	{
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		public Item next() {

			if(!hasNext())
				throw new NoSuchElementException("there are no more items to return"); 
			return null;
		}

		public void remove() {
			throw new UnsupportedOperationException("not implemented");
		}
		/*
		 private Node current = first;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
		 * */
	}
		
	public static void main(String[] args) {
		Deque<Integer> q = new Deque<Integer>();
		q.addFirst(3);
		q.addFirst(2);
		q.addFirst(1);
		
		q.addLast(4);
		q.addLast(5);
		q.addLast(6);
		q.addLast(7);
		
		q.removeFirst();
		
		//q.removeFirst();
		//q.removeFirst();
		
		StdOut.println(q.toString());
	}
	
	

}
