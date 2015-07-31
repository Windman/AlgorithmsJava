import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Item[] q; // queue elements
	private int N = 0; // number of elements on queue
	private int first = 0; // index of first element of queue
	private int last = 0; // index of next available slot

	public Deque() {
		q = (Item[]) new Object[2];
	}

	private void resizeLast(int max) {

		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i+first] = q[(first + i) % q.length];
		}
		q = temp;
		last = first+N;
		temp = null;
		// StdOut.println("resized: "+q.length);
	}

	private void resizeFirst(int max) {

		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++) {
			temp[i + N] = q[(first + i) % q.length];
		}
		q = temp;
		first = N;
		
		temp = null;
		// StdOut.println("resized: "+q.length);
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		// return the number of items on the deque
		return N;
	}

	public void addLast(Item item) {
		if(last<first || last < N)
			last = first+N;
		
		if (last == q.length) {
			resizeLast(2 * q.length);
		}
		
		q[last++] = item; // add item

		N++;
	}

	public void addFirst(Item item) {
		if (first == 0) {
			resizeFirst(2 * q.length);
			first = q.length - N;
		}
		
		q[--first] = item;
		N++;
	}

	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");

		Item item = q[first];
		q[first] = null; // to avoid loitering
		N--;
		first++;
		if (first == q.length)
			first = 0; // wrap-around
		if (N > 0 && N == q.length / 4)
			resizeFirst(q.length / 2);
		return item;
	}

	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		
		if (q[last] == null) {
			last--;
		}
		Item item = q[last];
		q[last--] = null;
		N--;
		
		if (N > 0 && N == q.length / 4)
			resizeLast(q.length / 2);
		return item;
	}

	/*
	 * public String toString() { StringBuilder s = new StringBuilder(); for
	 * (Item item : this) s.append(item + " "); return s.toString(); }
	 */
	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private int i = 0;

		public boolean hasNext() {
			return i < N;
		}

		public void remove() {
			throw new UnsupportedOperationException("not implemented");
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException(
						"there are no more items to return");
			Item item = q[(i + first) % q.length];
			i++;
			return item;
		}
	}
}
