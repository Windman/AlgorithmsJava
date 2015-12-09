import java.util.*;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;
import java.math.*;

//In this exercice, your mission consists in finding the minimal amount of hours it would take for a message 
//to propagate across the entire network given to you as input
class Solution {

    //Don't copy to CG this main method
	public static void main(String args[]) {
        In in = new In(args[0]);
        int n = in.readInt();
        
        Graph G = new Graph(n);
        
        for (int i = 0; i < n; i++) {
            int v = in.readInt();
            int w = in.readInt();
            G.addEdge(v, w);
        }
        
        int s = 1;
        
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);
        
        for (int v = 0; v < G.V(); v++) {
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
                for (int x : bfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d (-):  not connected\n", s, v);
            }

        }
        
        System.out.println(G);
    }
    
    static class BreadthFirstSearch {
        private static final int INFINITY = Integer.MAX_VALUE;
        private boolean[] marked;  // marked[v] = is there an s-v path
        private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
        private int[] distTo;      // distTo[v] = number of edges shortest s-v path

        /**
         * Computes the shortest path between the source vertex <tt>s</tt>
         * and every other vertex in the graph <tt>G</tt>.
         * @param G the graph
         * @param s the source vertex
         */
        public BreadthFirstSearch(Graph G, int s) {
            marked = new boolean[G.V()];
            distTo = new int[G.V()];
            edgeTo = new int[G.V()];
            bfs(G, s);
        }

        /**
         * Computes the shortest path between any one of the source vertices in <tt>sources</tt>
         * and every other vertex in graph <tt>G</tt>.
         * @param G the graph
         * @param sources the source vertices
         */
        public BreadthFirstSearch(Graph G, Iterable<Integer> sources) {
            marked = new boolean[G.V()];
            distTo = new int[G.V()];
            edgeTo = new int[G.V()];
            for (int v = 0; v < G.V(); v++)
                distTo[v] = INFINITY;
            bfs(G, sources);
        }

        // breadth-first search from a single source
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

        // breadth-first search from multiple sources
        private void bfs(Graph G, Iterable<Integer> sources) {
            Queue<Integer> q = new Queue<Integer>();
            for (int s : sources) {
                marked[s] = true;
                distTo[s] = 0;
                q.enqueue(s);
            }
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

        /**
         * Is there a path between the source vertex <tt>s</tt> (or sources) and vertex <tt>v</tt>?
         * @param v the vertex
         * @return <tt>true</tt> if there is a path, and <tt>false</tt> otherwise
         */
        public boolean hasPathTo(int v) {
            return marked[v];
        }

        /**
         * Returns the number of edges in a shortest path between the source vertex <tt>s</tt>
         * (or sources) and vertex <tt>v</tt>?
         * @param v the vertex
         * @return the number of edges in a shortest path
         */
        public int distTo(int v) {
            return distTo[v];
        }

        /**
         * Returns a shortest path between the source vertex <tt>s</tt> (or sources)
         * and <tt>v</tt>, or <tt>null</tt> if no such path.
         * @param v the vertex
         * @return the sequence of vertices on a shortest path, as an Iterable
         */
        public Iterable<Integer> pathTo(int v) {
            if (!hasPathTo(v)) return null;
            Stack<Integer> path = new Stack<Integer>();
            int x;
            for (x = v; distTo[x] != 0; x = edgeTo[x])
                path.push(x);
            path.push(x);
            return path;
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
    }
    static class Graph
    {
    	public int V;
        public int E;
        public List<Integer>[] adj;
        
        public Graph(int n) {
            if (n < 0) throw new IllegalArgumentException("vertices");
            this.V = n + 1;
            this.E = 0;
            adj = (ArrayList<Integer>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<Integer>();
            }
        }
    	
        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
        
        public int V(){
        	return V;
        }
        
    	public void addEdge(int v, int w){
    		E++;
            adj[v].add(w);
            adj[w].add(v);
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