package alg;

import edu.princeton.cs.algs4.StdOut;

public class ClockAngleProblem {

	public static void main(String[] args) {
		StdOut.println(angle(5,24));
	}
	
	static double angle(int h, int m) {
	    double hAngle = 0.5D * (h * 60 + m);
	    double mAngle = 6 * m;
	    double angle = Math.abs(hAngle - mAngle);
	    angle = Math.min(angle, 360 - angle);
	    return angle;
	}
}
