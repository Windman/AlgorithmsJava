/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

	private final class BySlope implements Comparator<Point>
    {
		private Point p;
		
		public BySlope(Point pt)
		{
			p = pt;
		}
		
		public int compare(Point o1, Point o2) {
			
			double value = p.slopeTo(o1) - p.slopeTo(o2);
									
			if (value > 0)
				return 1;
			else if (value < 0)
				return -1;
			else return 0;
		}
    }
	
	// compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope(this);       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }
    	
    	double ys = that.y - this.y;
    	double xs = that.x - this.x;
    	
    	if (xs == 0 && ys > 0) 
    		return Double.POSITIVE_INFINITY;
    	else if (xs == 0 && ys < 0)
    		return Double.POSITIVE_INFINITY;
    	else if (xs > 0 && ys == 0) 
    		return 0.0;
    	else if (xs < 0 && ys == 0)
    		return 0.0;
    	else if (xs == 0 || ys == 0)
    		return Double.NEGATIVE_INFINITY;
    	else return ys/xs;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	if (that == null) {
            throw new NullPointerException();
        }
    	
    	if(this.y < that.y || this.y == that.y && this.x < that.x) {
    		return -1;
    	}
    	else if (this.y == that.y || this.x == that.x)
    		return 0;
		else return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        Point p1 = new Point(10000, 0);
        Point p2 = new Point(6000, 7000);
        Point p3 = new Point(0, 10000);
        
        StdOut.println(p1.slopeTo(p2));
        StdOut.println(p1.slopeTo(p3));
        
    }
}