import java.util.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.math.*;

//https://www.quora.com/Graph-Theory/What-is-the-most-efficient-algorithm-for-finding-the-center-of-a-tree
//In this exercice, your mission consists in finding the minimal amount of hours it would take for a message 
//to propagate across the entire network given to you as input
class Solution {
   
	public static void main(String args[]) {
        In in = new In(args[0]);
        int n = in.readInt();
        
        Graph G = new Graph(n);
        
        for (int i = 0; i < n; i++) {
            int v = in.readInt();
            int w = in.readInt();
            G.addEdge(v, w);
        }
        
        int c = G.findCenter();
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(G,c);
        
        int max = bfs.distances()[0];
        for (int i = 1; i < bfs.distances().length; i++) {
            if (bfs.distances()[i] != Integer.MAX_VALUE && bfs.distances()[i] > max) {
              max = bfs.distances()[i];
            }
        }
        
        //System.out.println(G);
        System.out.println("Center: "+c);   
        System.out.println(max);    
    }
    
	static class BreadthFirstSearch {
        private static final int INFINITY = Integer.MAX_VALUE;
        private boolean[] marked;  
        private int[] edgeTo;      
        private int[] distTo;      
        
        public BreadthFirstSearch(Graph G, int s) {
            marked = new boolean[G.V()];
            distTo = new int[G.V()];
            edgeTo = new int[G.V()];
            bfs(G, s);
        }
        
        private void bfs(Graph G, int s) {
            Queue<Integer> q = new Queue<Integer>();
            for (int v = 0; v < G.V(); v++)
                distTo[v] = INFINITY;
            distTo[s] = 0;
            marked[s] = true;
            q.enqueue(s);

            while (!q.isEmpty()) {
                int v = q.dequeue();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        marked[w] = true;
                        q.enqueue(w);
                    }
                }
            }
        }
               
        public int[] distances() {
            return distTo;
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
    
	static class Graph
    {
    	public int V;
        public int E;
        public List<Integer>[] adj;
        private Integer[] marked;
        private int adjLength;
        
        public Graph(int n) {
            if (n < 0) throw new IllegalArgumentException("vertices");
            this.V = n + 1;
            this.E = 0;
            marked = new Integer[V];
            adj = (ArrayList<Integer>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<Integer>();
                marked[v] = 0;
            }
            
            adjLength = adj.length;
        }
    	
        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
        
        public int V(){
        	return V;
        }
        
    	public void addEdge(int v, int w){
    		E++;
    		if (v > adjLength || w > adjLength)
    			resize(2*adjLength);
            adj[v].add(w);
            adj[w].add(v);
    	}
    	
    	@SuppressWarnings("unchecked")
		private void resize(int c) {
    		List<Integer>[] a;
    		a = (ArrayList<Integer>[]) new ArrayList[c];
    		Integer[] m = new Integer[c];
    		
    		for (int v = 0; v < c; v++) {
                a[v] = new ArrayList<Integer>();
                m[v] = 0;
            }
    		
    		for (int i = 0; i < V; i++) {
                a[i] = adj[i];
            }
            adj = a;
            
            for (int i = 0; i < V; i++) {
                m[i] = marked[i];
            }
            marked = m;
            
            adjLength = c;
            V = c;
        }   
    	
    	public int findCenter()
    	{
    		Queue<Integer> q = new Queue<Integer>();
    		for (int i = 1; i < adj.length; i++) {
				if (adj[i].size() == 1) {
					q.enqueue(i);
					marked[i] = 1;
				}
			}
    		
    		while(q.size() > 1 ) {
    			Integer e = q.dequeue();
    			    				
    			for(int v: adj[e] ) {
    				if (marked[v] == 0) {
    					q.enqueue(v);
    					marked[v] = 1;
    				}
    			}
    		}
    		
    		return q.dequeue();
    	}
    	
    	public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(V + " vertices, " + E + " edges " + "\r\n");
            for (int v = 0; v < V; v++) {
                s.append(v + ": ");
                for (int w : adj[v]) {
                    s.append(w + " ");
                }
                s.append("\r\n");
            }
            return s.toString();
        }
    	
    }
}