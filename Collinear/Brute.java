import java.util.Arrays;

public class Brute {
	
	private static void init()
	{
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01); 		
	}
	
	public static void main(String[] args) {
		
		init();
		//String filename = "C:\\_SourcesJava\\AlgorithmsJava\\Collinear\\Tests\\input200.txt";// args[0];
		String filename = args[0];
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
        for (int i = 0; i < points.length; i++) {
        	for (int j = i; j < points.length; j++) {
        		if (i == j) continue;
        		for (int k = j; k < points.length; k++) {
        			if (j == k) continue;
        			for (int g = k; g < points.length; g++) {
        				if (k == g) continue;
        				if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k]) 
        						&& points[i].slopeTo(points[j]) == points[k].slopeTo(points[g])) {
        					StdOut.println(points[i]+" -> "+points[j]+" -> "+ points[k]+" -> "+points[g]);
        					points[i].drawTo(points[g]);
        					StdDraw.show(0);
        				}
            		}
        		}
            }
        }
        
    }
}
