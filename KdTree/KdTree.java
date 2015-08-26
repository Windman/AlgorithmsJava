import org.w3c.dom.Node;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

//https://github.com/siyuano/coursera-algs4/blob/master/algs4-part1/week5-kdtree/src/main/java/KdTree.java
	
public class KdTree {

	private Node root; // root of 2d-tree
	private int size;
	private static RectHV defaultRt;
	
	public KdTree() {
		size = 0;
		root = null;
		defaultRt = new RectHV(0.0, 0.0, 1.0, 1.0);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		root = insert(null, root, p, true, defaultRt);
  	}
	
	private Node insert(Node parent, Node node, Point2D p, boolean odd, RectHV r) {
		if (node == null) {
			node = new Node(parent, p, null, null, odd, new RectHV(r.xmin(), r.ymin(), p.x(), r.ymax()));
			size++;
		}

		if (p.equals(node.p))
			return node;
		
		if (node.odd) {
			if (Point2D.X_ORDER.compare(p, node.p) > 0) {
				node.right = insert(node, node.right, p, !node.odd, new RectHV(r.xmin(), r.ymin(), p.x(), r.ymax()));
			}
			else {
				node.left = insert(node, node.left, p, !node.odd, new RectHV(r.xmin(), r.ymin(), r.xmax(), p.y()));
			}
		}
		else {
			if (Point2D.Y_ORDER.compare(p, node.p) > 0) {
				node.right = insert(node, node.right, p, !node.odd, new RectHV(p.x(), r.ymin(), r.xmax(), r.ymax()));
			}
			else {
				node.left = insert(node, node.left, p, !node.odd, new RectHV(r.xmin(), p.y(), r.xmax(), r.ymax()));
			}
		}
		
		return node;
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
		return false;
	}

	public void draw() {
		draw(root);
	}
	
	public void draw(Node n){
		if (n == null)
			return;
		
		StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        n.p.draw();
        
		Point2D from, to;
		
		if (n.odd) {
			StdDraw.setPenColor(StdDraw.RED);
			from = new Point2D(n.p.x(), n.rect.ymin());
            if (n.parent != null)
            	to = new Point2D(n.p.x(), n.parent.p.y());
            else to = new Point2D(n.p.x(), n.rect.ymax());
		}
		else { 
			StdDraw.setPenColor(StdDraw.BLUE);
			from = new Point2D(n.rect.xmin(), n.p.y());
			if (n.parent != null)
				to = new Point2D(n.parent.p.x(), n.p.y());
			else to = new Point2D(n.rect.xmax(), n.p.y());
		}
		
        StdDraw.setPenRadius(.005);
		from.drawTo(to);
		
		draw(n.left);
		draw(n.right);
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle
		Queue<Point2D> q = new Queue<Point2D>();
		return q;
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		return new Point2D(0,0);
	}
	
	private static class Node {
		   private Node parent;
		   private Point2D p;     
		   private boolean odd;
		   private Node left;       
		   private Node right;       
		   private RectHV rect;
		   
		   public Node(Node parent, Point2D p, Node lb, Node rt, boolean odd, RectHV rect){
			   this.parent = parent;
			   this.p = p;
			   this.left = lb;
			   this.right = rt;
			   this.odd = odd;
			   this.rect = rect;
		   }
		   
		   @Override
		   public String toString() {
			   return p.toString();
		   }
	}
	
	public static void main(String[] args) {
		KdTree t = new KdTree();
		t.insert(new Point2D(.7,.2));
		t.insert(new Point2D(.5,.4));
				
		t.draw();
		
		StdOut.println("OK");
	}

}
