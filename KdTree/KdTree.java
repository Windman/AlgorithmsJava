import org.w3c.dom.Node;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {

	private Node root; 
	private int size;
	private static RectHV defaultRt;
	
	private final double dotPr, linePr;
	
	public KdTree() {
		size = 0;
		root = null;
		defaultRt = new RectHV(0.0, 0.0, 1.0, 1.0);
		dotPr = .01;
		linePr = .005;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		root = insert(root, p, true);
  	}
	
	private Node insert(Node node, Point2D p, boolean odd) {
		if (node == null) {
			node = new Node(p, null, null, odd);
			size++;
		}

		if (p.equals(node.p))
			return node;
		
		if (node.odd) {
			if (Point2D.X_ORDER.compare(p, node.p) > 0) {
				node.right = insert(node.right, p, !node.odd);
			}
			else {
				node.left = insert(node.left, p, !node.odd);
			}
		}
		else {
			if (Point2D.Y_ORDER.compare(p, node.p) > 0) {
				node.right = insert(node.right, p, !node.odd);
			}
			else {
				node.left = insert(node.left, p, !node.odd);
			}
		}
		
		return node;
	}

	public boolean contains(Point2D p) {
		Node node = root;
		while(true) {
			if (node == null)
				return false;
			
			if (p.equals(node.p)) {
                return true;
            }
			
			if (node.odd) {
				if (Point2D.X_ORDER.compare(p, node.p) > 0) {
					node = node.right;
				}
				else {
					node = node.left; 
				}
			}
			else {
				if (Point2D.Y_ORDER.compare(p, node.p) > 0) {
					node = node.right; 
				}
				else {
					node = node.left; 
				}
			}
		}
	}

	public void draw() {
		draw(root, defaultRt);
	}
	
	private void draw(Node n, RectHV r){
		if (n == null)
			return;
		
		StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(dotPr);
        n.p.draw();
        
		Point2D from, to;
		
		if (n.odd) {
			StdDraw.setPenColor(StdDraw.RED);
			from = new Point2D(n.p.x(), r.ymin());
            to = new Point2D(n.p.x(), r.ymax());
		}
		else { 
			StdDraw.setPenColor(StdDraw.BLUE);
			from = new Point2D(r.xmin(), n.p.y());
            to = new Point2D(r.xmax(), n.p.y());
		}
		
        StdDraw.setPenRadius(linePr);
        from.drawTo(to);
        
		draw(n.left, leftRectangle(n,r));
		draw(n.right, rightRectangle(n,r));
	}
	
	private RectHV leftRectangle(Node n, RectHV r) {
        return n.odd
                ? new RectHV(r.xmin(), r.ymin(), n.p.x(), r.ymax())
                : new RectHV(r.xmin(), r.ymin(), r.xmax(), n.p.y());
    }

    private RectHV rightRectangle(Node node, RectHV nodeRect) {
        return node.odd
                ? new RectHV(node.p.x(), nodeRect.ymin(), nodeRect.xmax(), nodeRect.ymax())
                : new RectHV(nodeRect.xmin(), node.p.y(), nodeRect.xmax(), nodeRect.ymax());
    }
    
	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle
		Queue<Point2D> q = new Queue<Point2D>();
		range(rect, root, defaultRt, q);
		return q;
	}

	private void range(RectHV queryRect, Node n, RectHV r, Queue<Point2D> ptsQueue) {
		if (n == null) {
            return;
        }

        if (queryRect.intersects(r)) {
            if (queryRect.contains(n.p)) {
            	ptsQueue.enqueue(n.p);
            }

            range(queryRect, n.left, leftRectangle(n, r), ptsQueue);
            range(queryRect, n.right, rightRectangle(n, r), ptsQueue);
        }
	}
	
	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		return new Point2D(0,0);
	}
	
	private static class Node {
		   private Point2D p;     
		   private boolean odd;
		   private Node left;       
		   private Node right;       
		    
		   public Node(Point2D p, Node lb, Node rt, boolean odd){
			   this.p = p;
			   this.left = lb;
			   this.right = rt;
			   this.odd = odd;
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
		t.insert(new Point2D(.1,.2));
		t.insert(new Point2D(.3,.5));
		t.insert(new Point2D(.6,.6));
		t.insert(new Point2D(.5,.9));
		
		StdOut.println("Contains: " + t.contains(new Point2D(.5,.9)));
		
		StdOut.println("OK");
	}

}
