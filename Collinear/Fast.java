import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Fast {

	private static void Init() {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.show(0);
		StdDraw.setPenRadius(0.01);
	}

	public static void main(String[] args) {
		Init();
		// !!! Before publish change to args[0]
		String filename = "C:\\_SourcesJava\\AlgorithmsJava\\Collinear\\Tests\\input20.txt";// args[0];
		// String filename = args[0];
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
		
		ArrayList<Point> map =new ArrayList<Point>();
		Stack<Double> st = new Stack<Double>();
		for (int i = 0; i < points.length; i++) {
			Arrays.sort(points, points[i].SLOPE_ORDER);
			for (int j = i + 1; j < points.length; j++) {
				if (i == j)
					continue;
				for (int k = 1; k < points.length; k++) {
					if (j == k)
						continue;
					
					if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
						if (!map.contains(points[j])) {
							map.add(points[j]);
						}
						//StdOut.println(points[i] + " " + points[j].toString()+ " " + points[i].slopeTo(points[j]));
						
						//Draw
						if (map.size()>2) {
							//Print
							map.add(points[i]);
							Collections.sort(map);
							
							Point first = map.get(0);
							Point last = map.get(map.size()-1);
							
							for(Point p: map) {
								StdOut.print(p.toString());
							}
							StdOut.println();
							
							first.drawTo(last);
							StdDraw.show(0);
							map.clear();
						}
						break;
					}
				} //end k for
			} //end j for
			StdOut.println();
		}
		StdOut.println();
	}
}
