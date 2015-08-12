import java.util.Arrays;
import java.util.Comparator;

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

		double pSlope1 = 0.0;
		double pSlope2 = 0.0;
		
		Point first = null;
		Point last = null;
				
		for (int i = 0; i < points.length; i++) {
			Arrays.sort(points, i, points.length, points[i].SLOPE_ORDER);
			first = points[i];
			pSlope1 = points[i].slopeTo(points[i+1]);
			for (int j = i; j < points.length; j++) {
				if (i != j) {
					pSlope2 = points[i].slopeTo(points[j]);
					StdOut.println(points[i] +" "+ points[j].toString() +" "+ points[i].slopeTo(points[j]));
					
					if (pSlope1 != pSlope2) {
						pSlope1 = pSlope2;
						i = j-1;
						last = points[i];
						StdOut.println("Stop index: "+i);
						break;
					}
					if (j == points.length-1){
						last = points[j];
						i = j;
					}
				}
			}
			StdOut.println();
			first.drawTo(last);
			StdDraw.show(0);
			first = null;
			last = null;
		}
		StdOut.println();
		/*for (int i = 0; i < points.length; i++) {
		Arrays.sort(points, 0, points.length, points[i].SLOPE_ORDER);
				
			//points[index].drawTo(points[s.peek()]);
			//StdDraw.show(0);
		}*/
	}
}
