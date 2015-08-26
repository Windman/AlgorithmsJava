import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {

    private SET<Point2D> s;

	public PointSET() {
		s = new SET<Point2D>();
	}

	public boolean isEmpty() {
		return s.isEmpty();
	}

	public int size() {
		return s.size();
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (p == null) throw new java.lang.NullPointerException();
		if (!s.contains(p))
			s.add(p);
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
		if (p == null) throw new java.lang.NullPointerException();
		return s.contains(p);
	}

	public void draw() {
		// draw all points to standard draw
		for (Point2D p: s) {
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle
		if (rect == null) throw new java.lang.NullPointerException();

		Queue<Point2D> q = new Queue<Point2D>();
		
		for (Point2D p: s) {
			if(rect.contains(p)) {
				q.enqueue(p);
			}
		}

		return q;
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		if (p == null) throw new java.lang.NullPointerException();

		if (s.isEmpty())
			return null;
		Point2D nearest = null;
		for (Point2D point: s) {
			if (nearest == null)
				nearest = point;
			else {
				if (p.distanceTo(point) < p.distanceTo(nearest))
					nearest = point;
			}
		}
		return nearest;
	}

	public static void main(String[] args) {
		/*Point2D pp = new Point2D(.81, .30);
		String filename = args[0];
		In in = new In(filename);

        StdDraw.show(0);

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);
        }
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        
        Point2D nearest = brute.nearest(pp);
        StdOut.println(nearest.toString());
        StdDraw.show(0);*/
	}
}
