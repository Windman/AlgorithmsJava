package alg;

import edu.princeton.cs.algs4.StdOut;

public class MyList<Item> {

	private static class Node<Item> {
		
		public Node(Item v) {
			value = v;
		}
		
		Item value;
		Node next;
	}
	
	private Node root;
	
	public void add(Item v) {
		if (v == null) throw new NullPointerException();
		
		if (root == null) {
			root = new Node(v);
		} else 	root = insert(root, v);
	}
	
	public Node insert(Node n, Item v) {
		if (n.next == null)
			n.next = new Node(v);
		else insert(n.next,v);
		
		return n;
	}
	
	public void print()
	{
		for(Node x = root; x != null; x = x.next) {
			StdOut.println(x.value);
		}
	}
	
	public static void main(String[] args) {
		MyList l = new MyList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		
		l.print();

	}

}
