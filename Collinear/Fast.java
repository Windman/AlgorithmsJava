import java.util.ArrayList;
import java.util.Arrays;

public class Fast {
	
	private static void init() {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01);
	}
	
	public static void main(String[] args) {
		init();
		// !!! Before publish change to args[0]7
		String filename = "C:\\_SourcesJava\\AlgorithmsJava\\Collinear\\Tests\\input8.txt";// args[0];
		//String filename = args[0];
		In in = new In(filename);
		int N = in.readInt();
		Point[] points = new Point[N];
		
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point p = new Point(x, y);
			p.draw();
			points[i] = p;
		}
		StdDraw.show(0);
		StdDraw.setPenRadius();
		
		Arrays.sort(points);
		
		Point[] sorted = new Point[N];
		System.arraycopy(points, 0, sorted, 0, N);
		
		ArrayList<PointPath> map = new ArrayList<Fast.PointPath>();
				
		for (int i = 0; i < points.length -3; i++) {
			System.arraycopy(sorted, 0, points, 0, N);
			Arrays.sort(points, points[i].SLOPE_ORDER);
			
			int idx = 1;
			//int j = 2;
			double slope1 = points[0].slopeTo(points[idx]);
			
			for (int j = 2; j< points.length;) {
			//while (j < points.length) {
				double slope2 = points[0].slopeTo(points[j]);
				
				if (slope1 != slope2 || j == points.length -1) {
					if (slope1 == slope2) {
						j++;
					}
					
					if (j >= idx +3 && !isInPath(map, points[0], slope1))
					{
						StdOut.print(points[0]);
						addPoint(map, points[0], slope1);
						
						for (int z = idx; z < j; z++) {
                            System.out.print(" -> " + points[z]);
                            addPoint(map, points[z], slope1);
                        }
						
						points[0].drawTo(points[j-1]);
						StdDraw.show(0);
						
						StdOut.println();
					}
					idx = j;
					slope1 = slope2;
				}
				j++;
			}
		}
		/*
		for(Point p: map.keySet())
		{
			StdOut.print(p);
		}*/
	}
		
	private static boolean isInPath(ArrayList<PointPath> map, Point p, double slope) {
		for (PointPath pp: map) {
			if (pp.point.compareTo(p) == 0) {
				for(double s: pp.slopes) {
					if (s == slope)
						return true;
				}
			}
		}
		
		return false;
	}
	
	private static void addPoint(ArrayList<PointPath> map, Point p, double slope) {
		PointPath path = new PointPath();
		path.point = p;
		path.slopes.add(slope);
		map.add(path);
	}
	
	private static final class PointPath {
		
		PointPath()
		{
			slopes = new ArrayList<Double>();
		}
		
		public Point point;
		public ArrayList<Double> slopes;
	}
}
