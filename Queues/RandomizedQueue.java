import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int N; // number of elements on queue
	private Node first; // beginning of queue
	private Node last;

	private class Node {
		private Item item;
		private Node next;
	}

	public RandomizedQueue() {
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		// add the item
		if (item == null)
			throw new NullPointerException("Argument is empty");

		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		N++;
	}

	public Item dequeue() {
		// remove and return the item from the end
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");

		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

	public Item sample() {
		int index = StdRandom.uniform(N);
		int c = 0;
		for (Node x = first; x != null; x = x.next) {
			if (c == index)
				return x.item;
			c++;
		}
		return null;
	}

	/*
	 * public String toString() { StringBuilder s = new StringBuilder(); for
	 * (Item item : this) s.append(item + " "); return s.toString(); }
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator(first);
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private Node current = first;

		RandomizedQueueIterator(Node f) {
			first = f;
		}

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {

			if (!hasNext())
				throw new NoSuchElementException(
						"there are no more items to return");

			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException("not implemented");
		}
	}
}
