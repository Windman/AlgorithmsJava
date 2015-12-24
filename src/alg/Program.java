package alg;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Program {

	public static void main(String[] args) {
		
		Integer R = 25;
		Integer L = 10;
		
		ArrayList<Integer> seq = new ArrayList<Integer>();
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(25);
		
		if (L == 1)
			seq.add(R);
		else
			for (int i = 1; i < L; i++) {
				seq = Generate(q);
				q = new Queue<Integer>();
				for (Integer e: seq) {
					q.enqueue(e);
				}
			}
				
		int c = 1;
		for(int i:seq) {
			System.out.print(i);
			if (c != seq.size()) {
				System.out.print(" ");
				c++;
			}
		}
	}
	
	public static ArrayList<Integer> Generate(Queue<Integer> seq) {
		ArrayList<Integer> newSeq = new ArrayList<Integer>();
		Integer value  = 0;
		Integer q = 1;
		Integer[] marked = new Integer[100];
		while(!seq.isEmpty()) {
			value = seq.dequeue();
			if (marked[value] == null) { //not marked
				//PreviousMarked
				AddElement(marked, newSeq, q);
				marked[value] = 1;
				q = 1;
			}
			else {
				q++;
			}
		}
		AddElement(marked, newSeq, q);
		return newSeq;
	}
	
	static void AddElement(Integer[] marked, ArrayList<Integer> newSeq, Integer q){
		for (int i = 0; i < marked.length; i++) {
			if (marked[i] != null) {
				newSeq.add(q);
				newSeq.add(i);
				marked[i] = null;
			}
		} 
	}
	
	static class Queue<Item> {
	    
		private int N;               
	    private Node<Item> first;    
	    private Node<Item> last;     
	    
	    private static class Node<Item> {
	        private Item item;
	        private Node<Item> next;
	    }
	    
	    public Queue() {
	        first = null;
	        last  = null;
	        N = 0;
	    }
	    
	    public boolean isEmpty() {
	        return first == null;
	    }
	    
	    public int size() {
	    	return N;
	    }
	    
	    public void enqueue(Item item) {
	        Node<Item> oldlast = last;
	        last = new Node<Item>();
	        last.item = item;
	        last.next = null;
	        if (isEmpty()) first = last;
	        else           oldlast.next = last;
	        N++;
	    }
	    
	    public Item dequeue() {
	        if (isEmpty()) throw new NoSuchElementException("underflow");
	        Item item = first.item;
	        first = first.next;
	        N--;
	        if (isEmpty()) last = null;   
	        return item;
	    }
	    
	    public String toString() {
	        StringBuilder s = new StringBuilder();
	        for (Node x = first; x != null; x = x.next)
	        {
	        	s.append(x.item);
	        	s.append(" ");
	        }
	        return s.toString();
	    }
	}
}



