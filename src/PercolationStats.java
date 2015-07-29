/**
 * @author Maksim Kilovatiy PercolationStats implementation perform T
 *         independent experiments on an N-by-N grid
 */
public class PercolationStats {

	private int size;

	private double numberOfExperiments;
	private double gridSide;
	private double[] threshold;

	/**
	 * @param n
	 *            - grid size
	 * @param t
	 *            - number of expeiments
	 */
	public PercolationStats(int n, int t) {
		if (n <= 0 || t <= 0)
			throw new java.lang.IllegalArgumentException("n or t are <= 0");
		numberOfExperiments = t;
		size = n;
		gridSide = size * size;

		threshold = new double[t];
		for (int i = 0; i < t; i++) {
			double percolationPoint = 1;
			Percolation engine = new Percolation(n);
			while (true) {
				int x = StdRandom.uniform(n + 1);
				int y = StdRandom.uniform(n + 1);
				if (x == 0 || x > size || y == 0 || y > size)
					continue;
				else if (engine.isOpen(x, y))
					continue;
				else {
					engine.open(x, y);
					percolationPoint++;
				}
				if (engine.percolates())
					break;

			}
			threshold[i] = percolationPoint / gridSide;
		}
	}

	/**
	 * @return sample mean of percolation threshold public double
	 */
	public double mean() {
		double summ = 0;
		for (int i = 0; i < threshold.length; i++) {
			summ += threshold[i];
		}
		return summ / numberOfExperiments;
	}

	/**
	 * @return sample standard deviation of percolation threshold
	 */
	public double stddev() {
		double summ = 0;
		double m = mean();
		for (int i = 0; i < threshold.length; i++) {
			summ += (threshold[i] - m) * (threshold[i] - m);
		}
		return summ / (numberOfExperiments - 1);
	}

	/**
	 * @return low endpoint of 95% confidence interval
	 */
	public double confidenceLo() {
		return mean()
				- (1.96 * Math.sqrt(stddev()) / Math.sqrt(numberOfExperiments));
	}

	/**
	 * @return high endpoint of 95% confidence interval
	 */
	public double confidenceHi() {
		return mean()
				+ (1.96 * Math.sqrt(stddev()) / Math.sqrt(numberOfExperiments));
	}

	public static void main(String[] args) {
		int n = 50;// Integer.parseInt(args[0]);
		int t = 20; // Integer.parseInt(args[1]);
		PercolationStats s = new PercolationStats(n, t);
		StdOut.println("mean = " + s.mean());
		StdOut.println("stddev = " + s.stddev());
		StdOut.println("95% confidence interval = " + s.confidenceLo() + ", "
				+ s.confidenceHi());
	}

}
